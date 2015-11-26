package common.view;

import common.bo.FriendHandler;
import common.bo.ProfileHandler;
import common.bo.UserHandler;
import common.bo.WallHandler;
import common.model.Profile;
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


    private String name;
    private String pass;
//    private Profile profile;
    private String searchName;
    private String profileName;
    private int age;
    private String desc;
    private boolean isFemale;
    private String post;

    public int getnFollowers() {
        int out=0;
        //out = FriendHandler.getFollowers(name).size();//TODO errors from here
        return out;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getProfileName() {
        return profileName;
    }

    public int getAge() {
        return age;
    }

    public String getDesc() {
        return desc;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSearchName(String searchName){
        this.searchName=searchName;
    }

    public String getSearchName(){
        return searchName;
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

    public void setPass(String pass) {
        this.pass = pass;
    }

    /* public Profile getProfile() throws IOException, ClassNotFoundException {
        return ProfileHandler.getProfile(name);//TODO ajabaja inte skicka model lager
                                                //TODO även om det är en kopia
    }*/

    public String postToWall(){
        //do post
        WallHandler.post(name,post);
        post="";
        return "home";
    }

    public String update(){
        //TODO do save stuff here
        ProfileHandler.update(profileName,age,desc,isFemale,name);
        return "home";
    }

    public Collection<WallPost> getWall(){
        return WallHandler.getPosts(name);
    }

    public void setGender(String gender){
        if(gender.contains("female")){
            isFemale=true;
        }else{
            isFemale=false;
        }
    }

    public String getGender() throws IOException, ClassNotFoundException {
        if (isFemale){
            return "woman";
        }else{
            return "man";
        }
    }

    public Collection<Profile> getResults() throws IOException, ClassNotFoundException {
        return ProfileHandler.search(searchName);//TODO fuuuuuuuu not safe at all, actuall list of all user with name
    }

    public String addFriend(Profile p){
        System.out.println(p);
        //TODO add friend here
        FriendHandler.addFollower(name,p.getUser().getUsername());
        return "home";//mabey profile/uid here?
    }

    public String login() throws IOException, ClassNotFoundException  {
        if (UserHandler.login(name, pass)) {
            System.out.println("logged in");
            Profile tmp= ProfileHandler.getProfile(name);
            profileName=tmp.getName();
            age=tmp.getAge();
            desc=tmp.getDescription();
            isFemale=tmp.getIsFemale();
            return "home";
        }
        //TODO not logged in
        return "index";
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }

}
