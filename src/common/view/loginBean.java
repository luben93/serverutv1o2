package common.view;

import common.bo.*;
import common.viewModel.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    private String searchName="";
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

//    public long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost(){
        return post;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }




    //TODO here
//    public String getProfileName() {
//        return me.getName();
//    }
//    public String getAge() {
//        return me.getAge()+"";
//    }
//    public String getDesc() {
//        return me.getDesc();
//    }
//    public String getGender() {
//        me.getGender();
//    }
    public Collection<post> getWall(){
        List<post> out;
        out=WallHandler.getPosts(id);
        Collections.reverse(out);
        return out;

    }

    public profile getMe(){
        return me;
    }

    public Collection<post> getOtherWall(){
        List<post> out;
        out=WallHandler.getPosts(other.getUid());
        Collections.reverse(out);
        return out;

    }
    public void setSearchName(String name){
        searchName = name;
    }

    public String postToWall() {
        //do post
        WallHandler.post(new post(id,post));//TODO nullpointer
        post = "";
        return "home";
    }

    public String update() {
        //TODO do save stuff here
        ProfileHandler.update(me);
        return "home";
    }


    public Collection<message> getMessages(){
        List<message> out;
        message between = new message(id,other.getUid());
        out=chatHandler.getMessages(between);
        Collections.reverse(out);
        return out;
    }


    public void sendMessage (){
        message out = new message(id,other.getUid(),msg);
        chatHandler.sendMessage(out);
    }

    public Collection<profile> getResults() {
        if (searchName.equals("")) {
            return new ArrayList<profile>();
        }else{
           return ProfileHandler.search(new profile(searchName),me);
        }
    }

    public String showThisProfile(long otherID){
        other = getProfile(otherID);
        setSearchName("");
        return "profileInfo";
    }

    private profile getProfile(long id){
        return ProfileHandler.getProfile(id);
    }

    public String getnFollowers(){
        return FriendHandler.getFollowers(id).size()+"";
    }
    public String getnFollowing(){
        return FriendHandler.countFollowing(id)+"";
    }

    public String getSearchName(){
        return searchName;
    }

    public profile getShowProfile() {
        return other;
    }

    public String getOthernFollowers(){
        return FriendHandler.getFollowers(other.getUid()).size()+"";
    }

    public String getOthernFollowing(){
        return FriendHandler.countFollowing(other.getUid())+"";
    }

    public String addFriend(long otherID) {
        System.out.println(otherID);
        //TODO add friend here
        Follower follow =new Follower(id,otherID);
        FriendHandler.addFollower(follow);
//        Client cli = ClientBuilder.newClient();
//        WebTarget wt = cli.target("ip:port/path/rest/addFriend");
//        Response resp = wt.request().post(Entity.json(follow));
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
//        Client cli = ClientBuilder.newClient();
//        WebTarget wt = cli.target("ip:port/path/rest/login");
        ViewUser user = new ViewUser(username,pass);
        long tmp = UserHandler.login(user);
//        Response resp = wt.request().post(Entity.json(user));
//        System.out.println(resp);
//        System.out.println(resp.getStatus());
//        System.out.println(resp.getEntity());
//        long tmp=Long.valueOf(resp.getEntity().toString());

        if(tmp > 0){//success
            id=tmp;
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
