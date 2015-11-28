package common.view;

import common.bo.FriendHandler;
import common.bo.ProfileHandler;
import common.bo.WallHandler;
import common.model.Profile;
import common.model.User;
import common.model.WallPost;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by luben on 2015-11-07.
 */
@ManagedBean(name = "profileBean", eager = true)
@SessionScoped
public class profileBean {
    private Profile profile;
    private long id;
    private String searchName = "";

    profileBean(long id) throws IOException, ClassNotFoundException {
        this.id = id;
        profile = ProfileHandler.getProfile(id);
    }


    public Profile getShowProfile() throws IOException, ClassNotFoundException {
        return ProfileHandler.getProfile(id);
    }

    public int getnFollowers() {
        Collection<User> out;
        out = FriendHandler.getFollowers(profile.getUser().getU_id());//TODO errors from here

        return out.size();
    }

    public int getnFollowing() {
        return FriendHandler.countFollowing(profile.getUser().getU_id());
    }

//    public String getPost() {
//        return post;
//    }

    public String getProfileName() {
        return profile.getName();
    }

    public int getAge() {
        return profile.getAge();
    }

    public String getDesc() {
        return profile.getDescription();
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchName() {
        return searchName;
    }

    public Collection<WallPost> getWall() {
        return WallHandler.getPosts(profile.getUser().getU_id());
    }

    public String getGender() throws IOException, ClassNotFoundException {
        if (profile.getIsFemale()) {
            return "female";
        } else {
            return "male";
        }
    }

    boolean isFemale(){
        return profile.getIsFemale();
    }

    public Collection<Profile> getResults() throws IOException, ClassNotFoundException {
        if (searchName.equals("")) {
            return new ArrayList<Profile>();
        }
        return ProfileHandler.search(searchName, profile.getUser().getUsername());//TODO fuuuuuuuu not safe at all, actuall list of all user with searchName
    }


}
