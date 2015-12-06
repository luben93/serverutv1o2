package common.view;


import com.google.gson.Gson;
import common.viewModel.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
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
    private profile other;
    private Gson gson;



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

    public Collection<post> getWall() {
        return getWall(id);
    }

    public Collection<post> getOtherWall() {
        return getWall(other.getUid());
    }

    public String getnFollowers() {
        return getFollow(id,"ers");
    }

    public String getnFollowing() {
        return getFollow(id,"ing");
    }

    public String getOthernFollowers() {
        return getFollow(other.getUid(),"ers");
    }

    public String getOthernFollowing() {
        return getFollow(other.getUid(),"ing");
    }

    public String showThisProfile(long otherID) {
        other = getProfile(otherID);
        setSearchName("");
        return "profileInfo";
    }
    //TODO implementera rest härifrån och ner

    private Collection<post> getWall(long id) {
        List<post> out = null;
//        out = WallHandler.getPosts(id);
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/wall/" + id);// /me/other
        Response rsp = target.request().get();
        out = rsp.readEntity(new GenericType<List<post>>() {
        });
        Collections.reverse(out);
        return out;
    }

    public String postToWall() {
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/wall");
        target.request().post(Entity.entity(gson.toJson(new post(id,post)),MediaType.APPLICATION_JSON));//TODO check status
//        WallHandler.post(new post(id, post));//TODO nullpointer
        post = "";
        return "home";
    }

    public String update() {
        //TODO do save stuff here
        //ProfileHandler.update(me);
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/profile");
        target.request().post(Entity.entity(gson.toJson(me),MediaType.APPLICATION_JSON));//TODO check status
        return "home";
    }


    public Collection<message> getMessages() {
        List<message> out = null;
        //message between = new message(id, other.getUid());
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/chat/" + id + "/" + other.getUid());
        Response rsp = target.request().get();
        out = rsp.readEntity(new GenericType<List<message>>() {
        });
        //out = chatHandler.getMessages(between);
        Collections.reverse(out);
        return out;
    }


    public void sendMessage() {
        message out = new message(id, other.getUid(), msg);
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/chat");
        target.request().post(Entity.entity(gson.toJson(out),MediaType.APPLICATION_JSON));//TODO check status
//        Long tmp=resp.readEntity(Long.class);
//        chatHandler.sendMessage(out);
    }

    public Collection<profile> getResults() {
        if (searchName.equals("")) {
            return new ArrayList<profile>();
        } else {
            Client cli = ClientBuilder.newClient();
            WebTarget target = cli.target("http://localhost:8081/rest/profile/search/");
//            Response resp = target.request().post(Entity.json(new Search(searchName,me.getName())));
            Response resp = target.request().post(Entity.entity(gson.toJson(new Search(searchName,me.getName())),MediaType.APPLICATION_JSON));
            List<profile> tmp = resp.readEntity(new GenericType<List<profile>>() {
            });

            return tmp;
        }
    }



    private profile getProfile(long id) {

        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/profile/" + id);
        Response resp = target.request().get();

        return resp.readEntity(profile.class);
    }



//    private String getFollowers(long id) {
//        Client cli = ClientBuilder.newClient();
//        WebTarget target = cli.target("http://localhost:8081/rest/friends/followers/" + id);
//        Response resp = target.request().get();
//        List<profile> tmp = resp.readEntity(new GenericType<List<profile>>() {
//        });
//        return tmp + "";
//    }

    private String getFollow(long id,String ingOrErs) {
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/friends/follow"+ingOrErs+"/" + id);
        Response resp = target.request().get();
        List<profile> tmp = resp.readEntity(new GenericType<List<profile>>() {
        });
        return tmp + "";
       // return resp.readEntity(Integer.class) + "";
    }


    public String addFriend(long otherID) {
        System.out.println(otherID);
        //TODO add friend here
        Follower follow = new Follower(id, otherID);

        //FriendHandler.addFollower(follow);
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/friends");
        target.request().post(Entity.entity(gson.toJson(follow),MediaType.APPLICATION_JSON));//TODO check status maybe?

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
        user.doCrypt();

        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/users/login");
        gson=new Gson();
        System.out.println(gson.toJson(user));
        Response resp = target.request().post(Entity.entity(gson.toJson(user), MediaType.APPLICATION_JSON));
        String back= resp.readEntity(String.class);
        profile p= gson.fromJson(back,profile.class);
        long tmp = p.getUid();

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
