package common.view;

import common.bo.ProfileHandler;
import common.bo.UserHandler;
import common.model.Profile;

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

     public Profile getProfile() throws IOException, ClassNotFoundException {
        return ProfileHandler.getProfile(name);//TODO ajabaja inte skicka model lager
                                                //TODO även om det är en kopia
    }

    public String getGender() throws IOException, ClassNotFoundException {
        if (ProfileHandler.getProfile(name).getIsFemale()){
            return "woman";
        }else{
            return "man";
        }
    }

    public Collection getResults() throws IOException, ClassNotFoundException {
        return UserHandler.search(searchName);//TODO fuuuuuuuu not safe at all, actuall list of all user with name
    }


    public String login()  {
        if (UserHandler.login(name, pass)) {
            System.out.println("logged in");
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
