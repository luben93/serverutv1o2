package common.view;

import common.bo.ProfileHandler;
import common.bo.UserHandler;
import common.model.Profile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Created by luben on 2015-11-07.
 */
@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class loginBean {


    private String name;
    private String pass;

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

    public Profile getProfile(){
        return ProfileHandler.getProfile(name);
    }

    public String login() {
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
