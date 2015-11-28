package common.view;

import common.bo.FriendHandler;
import common.bo.ProfileHandler;
import common.bo.UserHandler;
import common.bo.WallHandler;
import common.model.Profile;
import common.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by luben on 2015-11-07.
 */
@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class loginBean {


    private String name = "";
    private String pass = "";
    private long id;
    private String post;
    private profileBean profile;


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


//    public String getName() {
//        return name;
//    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public String getPass() {
//        return pass;
//    }


    public void setPost(String post) {
        this.post = post;
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


    /* public Profile getProfile() throws IOException, ClassNotFoundException {
        return ProfileHandler.getProfile(name);//TODO ajabaja inte skicka model lager
                                                //TODO även om det är en kopia
    }*/

    public String postToWall() {
        //do post
        WallHandler.post(id, post);
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
//
//    public Collection<Profile> getResults() throws IOException, ClassNotFoundException {
//        if(searchName.equals("")){
//           return new ArrayList<Profile>();
//        }
//        Collection<Profile> out= ProfileHandler.search(searchName,name);//TODO fuuuuuuuu not safe at all, actuall list of all user with searchName
//
//        return out;
//    }


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
