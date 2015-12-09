package common.view;


import com.google.gson.Gson;
import common.viewModel.*;
import common.viewModel.ProfileList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
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

    private Collection<post> getWall(long id) {
        List<post> out = null;
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/wall/" + id);// /me/other
        Response rsp = target.request().get();
        String json=rsp.readEntity(String.class);
        out = gson.fromJson(json,PostList.class).getList();
        Collections.reverse(out);
        return out;
    }

    public String postToWall() {
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/wall");
        target.request().post(Entity.entity(gson.toJson(new post(id,post)),MediaType.APPLICATION_JSON));//TODO check status
        post = "";
        return "home";
    }

    public String update() {
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/profile");
        target.request().post(Entity.entity(gson.toJson(me),MediaType.APPLICATION_JSON));//TODO check status
        return "home";
    }


    public Collection<message> getMessages() {
        List<message> out = null;
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/chat/" + id + "/" + other.getUid());
        Response rsp = target.request().get();
        out = gson.fromJson(rsp.readEntity(String.class), MessageList.class).getList();

        //out = chatHandler.getMessages(between);
        Collections.reverse(out);
        return out;
    }


    public void sendMessage() {
        message out = new message(id, other.getUid(), msg);
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/chat");
        target.request().post(Entity.entity(gson.toJson(out),MediaType.APPLICATION_JSON));//TODO check status
        msg="";
    }

    public Collection<profile> getResults() {
        if (searchName.equals("")) {
            return new ArrayList<profile>();
        } else {
            Client cli = ClientBuilder.newClient();
            WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/profile/search/");
            Response resp = target.request().post(Entity.entity(gson.toJson(new Search(searchName,me.getName())),MediaType.APPLICATION_JSON));
            Collection<profile> tmp = gson.fromJson(resp.readEntity(String.class), ProfileList.class).getList();
            return tmp;
        }
    }



    private profile getProfile(long id) {

        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/profile/" + id);
        Response resp = target.request().get();
        return gson.fromJson(resp.readEntity(String.class),profile.class);
    }


    private String getFollow(long id,String ingOrErs) {
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/friends/follow"+ingOrErs+"/" + id);
        Response resp = target.request().get();
        String json = resp.readEntity(String.class);
        System.out.println("resp:"+json);
        ProfileList tmp = gson.fromJson(json, ProfileList.class);
        return tmp.getList().size() + "";
    }


    public String addFriend(long otherID) {
        System.out.println(otherID);
        Follower follow = new Follower(id, otherID);
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/friends");
        target.request().post(Entity.entity(gson.toJson(follow),MediaType.APPLICATION_JSON));//TODO check status maybe?
        return "home";
    }


    public String login() {
        ViewUser user = new ViewUser(username, pass);
        user.doCrypt();

        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://130.237.84.10:8081/starter/rest/users/login");
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
