package common.view;

import common.bo.*;
import common.model.ChatMessage;
import common.model.Profile;
import common.model.User;
import common.model.WallPost;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by luben on 2015-11-07.
 */
@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class loginBean {


    private String name = "";
    private String pass = "";
    private Long id;
    private String post;
    private profileBean profile;
    private profileBean showProfile;
    private String msg;


//    public void setShowProfile(String showProfile) {
//        this.showProfile = showProfile;
//    }
//
//    public Profile getShowProfile() throws IOException, ClassNotFoundException {
//        return ProfileHandler.getProfile(id);
//    }


//    public int getnFollowers() {
//        Collection<User> out;
//        out = FriendHandler.getFollowers(name);//TODO errors from here
//        for (User friend : out) {
//            System.out.println(friend);
//        }
//        return out.size();
//    }
//
//    public int getnFollowing(){
//        return FriendHandler.countFollowing(id);
//    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Collection<WallPost> getWall(){
        return profile.getWall();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost(){
        return post;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getProfileName() {
        return profile.getProfileName();
    }
    public String getAge() {
        return profile.getAge()+"";
    }
    public String getDesc() {
        return profile.getDesc();
    }
    public String getGender() throws IOException, ClassNotFoundException {
        return profile.getGender();
    }

    public void setSearchName(String name){
        profile.setSearchName(name);
    }


    /* public Profile getProfile() throws IOException, ClassNotFoundException {
        return ProfileHandler.getProfile(name);//TODO ajabaja inte skicka model lager
                                                //TODO även om det är en kopia
    }*/

    public String postToWall() {
        //do post
        WallHandler.post(id, post);//TODO nullpointer
        post = "";
        return "home";
    }

    public String update() throws IOException, ClassNotFoundException {
        //TODO do save stuff here
        ProfileHandler.update(profile.getProfileName(),profile.getAge(),profile.getDesc(),profile.isFemale(), id);
        return "home";
    }

//    public Collection<WallPost> getWall(){
//        return WallHandler.getPosts(id);
//    }
//
//    public void setGender(String gender){
//        if(gender.contains("female")){
//            isFemale=true;
//        }else{
//            isFemale=false;
//        }
//    }
//
//    public String getGender() throws IOException, ClassNotFoundException {
//        if (isFemale){
//            return "female";
//        }else{
//            return "male";
//        }
//    }



    public Collection<ChatMessage> getMessages(){
        return  profile.getMessages(id);
    }


    public void sendMessage (){
        profile.sendMessage(id,msg);
    }

    public Collection<Profile> getResults() throws IOException, ClassNotFoundException {
       return profile.getResults();
    }

    public profileBean getShowProfile(){
        return showProfile;
    }

    public String getnFollowers(){
        return  profile.getnFollowers()+"";
    }
    public String getnFollowing(){
        return  profile.getnFollowing()+"";
    }

    public String getSearchName(){
        return profile.getSearchName();
    }

    public String showProfile(Profile p) {
        try {
            showProfile=new profileBean(p.getU_id());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setSearchName("");
        return "profileInfo";
    }

    public String addFriend(Profile p) {
        System.out.println(p);
        //TODO add friend here
        FriendHandler.addFollower(id, p.getUser().getU_id());
        return "home";//mabey profile/uid here?
    }

   /* public Collection<WallPost> getFriendWall(String username){

    }*/

    public String login() throws IOException, ClassNotFoundException {
        User u = UserHandler.login(name, pass);
        if (u != null) {
            System.out.println("logged in");
            id=u.getU_id();
            profile = new profileBean(id);
            return "home";
        }
        //TODO not logged in
        return "index";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }

}
