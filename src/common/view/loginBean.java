package common.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
 //TODO git fuckup

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

    public Collection<TupleString> getWall(){
        return profile.getWall();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return "";
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
    public String getGender() {
        return profile.getGender();
    }

    public void setSearchName(String name){
        profile.setSearchName(name);
    }




    public String postToWall() {
        //do post
        //WallHandler.post(id, post);
        //TODO POST HERE
        post = "";
        return "home";
    }

    public String update()  {
        //ProfileHandler.update(profile.getProfileName(),profile.getAge(),profile.getDesc(),profile.isFemale(), id);
        //TODO profile update here
        return "home";
    }

    public String home(){
        showProfile=profile;
        return "home";
    }

    public Collection<TupleString> getMessages(){
        return  showProfile.getMessages(id);
    }


    public void sendMessage (){
        showProfile.sendMessage(id,msg);
    }

    public Collection<TupleString> getResults()  {
       return profile.getResults();
    }

    public profileBean getShowProfile(){
        //TODO behövs??
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

    public String showProfile(String p) {

        showProfile=new profileBean(Long.valueOf(p));

        setSearchName("");
        return "profileInfo";
    }

    public String addFriend(String p){//Profile p) {
        System.out.println(Long.valueOf(p));
        //TODO add friend here
        //FriendHandler.addFollower(id, Long.valueOf(p));
        return "home";//mabey profile/uid here?
    }

   /* public Collection<WallPost> getFriendWall(String username){

    }*/

    public String login()  {
       // User u = UserHandler.login(name, pass);
        //TODO login here
        Long u= new Long(1);
        if (u != null) {
            System.out.println("logged in");
            id=u;//u.getU_id();
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
