package common.view;


import common.viewModel.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by luben on 2015-11-07.
 */
@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class loginBean {


    private String username = "";
    private String pass = "";
    private Long id;
    private String post;
    private String msg;
    private String searchName = "";
    private profile me;
    private profile other;//?????????


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPass() {
        return pass;
    }

    public String getSearchName() {
        return searchName;
    }

    public profile getShowProfile() {
        return other;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public profile getMe() {
        return me;
    }

    public void setSearchName(String name) {
        searchName = name;
    }

    //TODO implementera rest härifrån och ner

    public Collection<post> getWall() {
        List<post> out = null;
        out = WallHandler.getPosts(id);
        Collections.reverse(out);
        return out;

    }


    public Collection<post> getOtherWall() {
        List<post> out = null;
        out = WallHandler.getPosts(other.getUid());
        Collections.reverse(out);
        return out;

    }


    public String postToWall() {
        WallHandler.post(new post(id, post));//TODO nullpointer
        post = "";
        return "home";
    }

    public String update() {
        //TODO do save stuff here
        ProfileHandler.update(me);
        return "home";
    }


    public Collection<message> getMessages() {
        List<message> out = null;
        message between = new message(id, other.getUid());
        out = chatHandler.getMessages(between);
        Collections.reverse(out);
        return out;
    }


    public void sendMessage() {
        message out = new message(id, other.getUid(), msg);
        chatHandler.sendMessage(out);
    }

    public Collection<profile> getResults() {
        if (searchName.equals("")) {
            return new ArrayList<profile>();
        } else {
            Client cli = ClientBuilder.newClient();
            WebTarget target = cli.target("http://localhost:8081/rest/profile/search/"+id+"/"+searchName);
            Response resp = target.request().get();
            List<profile> tmp=resp.readEntity(new GenericType<List<profile>>() {});

            return ProfileHandler.search(new profile(searchName), me);
        }
    }

    public String showThisProfile(long otherID) {
        other = getProfile(otherID);
        setSearchName("");
        return "profileInfo";
    }

    private profile getProfile(long id) {

        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/profile/"+id);
        Response resp = target.request().get();

        return resp.readEntity(profile.class);
    }

    public String getnFollowers() {
        return getFollowers(id);
    }

    public String getnFollowing() {
        return getFollowing(id);
    }

    public String getOthernFollowers() {
        return getFollowers(other.getUid());
    }

    public String getOthernFollowing() {
        return getFollowing(other.getUid());
    }

    private String getFollowers(long id){
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/friends/followers/"+id);
        Response resp = target.request().get();
        List<profile> tmp=resp.readEntity(new GenericType<List<profile>>() {});
        return tmp + "";
    }

    private String getFollowing(long id){
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/friends/following/"+id);
        Response resp = target.request().get();
        return resp.readEntity(Integer.class)+"";
    }



    public String addFriend(long otherID) {
        System.out.println(otherID);
        //TODO add friend here
        Follower follow = new Follower(id, otherID);

        //FriendHandler.addFollower(follow);
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/friends");
        target.request().post(Entity.json(follow));//TODO check status maybe?

        return "home";//mabey profile/uid here?
    }


    public String login() {
//        User u = UserHandler.login(username, pass);
//        if (u != null) {
//            System.out.println("logged in");
//            id=u.getU_id();
//            profile = new profileBean(id);
//            return "home";
//        }
//        //TODO not logged in
//        return "index";

        ViewUser user = new ViewUser(username, pass);

        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/users/login");
        Response resp = target.request().post(Entity.json(user));
        Long tmp=resp.readEntity(Long.class);


//        long tmp = UserHandler.login(user);

        if (tmp > 0) {//success
            id = tmp;
            me = getProfile(id);
            return "home";
        }
        return "index";

    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }

}
