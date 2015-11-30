package common.view;


import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by luben on 2015-11-07.
 */
//@ManagedBean(name = "profileBean", eager = true)
@SessionScoped
public class profileBean {

    private Long id;
    private String searchName = "";



    public profileBean(Long id)  {
        this.id = id;
    }

    public void setId(String Sid)  {
        id=Long.valueOf(Sid);
    }
/*
    public Profile getShowProfile() throws IOException, ClassNotFoundException {
        return ProfileHandler.getProfile(id);
    }
*/
    public int getnFollowers() {
//        Collection<User> out;
//        out = FriendHandler.getFollowers(id);
//TODO get followers
        return 2;
    }

    public int getnFollowing() {
        //return FriendHandler.countFollowing(id);
        //TODO following
        return 5;
    }

//    public String getPost() {
//        return post;
//    }

    public String getProfileName() {
        //TODO get profile name here
        System.out.println("getting name for" + id);

        return "user";//profile.getName();
    }

    public int getAge() {
        //TODO get profile age here
        System.out.println("getting age for" + id);
        return 20;//profile.getAge();
    }

    public long getId(){
        return id;
    }

    public String getDesc() {
        //TODO get profile desc here
        System.out.println("getting desc for" + id);
        return "nothing special";//profile.getDescription();
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchName() {
        return searchName;
    }

    public Collection<TupleString> getWall() {
        Collection<TupleString> out =new ArrayList<TupleString>();//WallHandler.getPosts(id);//TODO wall here
        out.add(new TupleString("1","hej"));
        Collections.reverse((List<TupleString>) out);
        System.out.println("wall for " +id);
        System.out.println(out);
        return out;
    }

    public List<TupleString> getMessages(long oid){
        //TODO get chat
        List<TupleString> out= new ArrayList<TupleString>();//chatHandler.getMessages(oid,profile.getU_id());
        out.add(new TupleString("1","hejhej"));
        for (TupleString cm: out) {
            System.out.println(cm);
        }
        Collections.reverse(out);

        return out;
    }

    public void sendMessage (long oid,String msg){
        //TODO chatt blandar ihop uid ibland kolla upp
        //chatHandler.sendMessage(oid,id,msg);
        //TODO send chat here
        System.out.println(oid+" said to "+id+" "+msg);
    }


    public String getGender()  {
        boolean isfemale=true;
        //TODO get isFemale here
        System.out.println("get gender");
        if (isfemale) {
            return "female";
        } else {
            return "male";
        }
    }

//    boolean isFemale(){
//        return profile.getIsFemale();
//    }

    public Collection<TupleString> getResults()  {
        if (searchName.equals("")) {
            return new ArrayList<TupleString>();
        }else{
            ArrayList<TupleString>out=new ArrayList<TupleString>();
            out.add(new TupleString("1","username"));
            System.out.println("username searched for "+searchName);
            return out;//placeholder

        }
        //return ProfileHandler.search(searchName, profile.getUser().getUsername());//TODO call search here
    }

}
