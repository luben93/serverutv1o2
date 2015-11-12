package common.view;

import common.model.User;
import common.model.UserHandler;
import common.view.loggedIn.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by luben on 2015-11-07.
 */
@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class loginBean {

    private UserHandler uh;
    private User user;

    public loginBean() {
        uh = new UserHandler();
    }

    public boolean isLoggedIn() {
        return uh.isLoggdIn();
    }

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

    public String login() {

        uh = new UserHandler();
        if (uh.login(name, pass)) {
            System.out.println("logged in");
            user=uh.getUser();
            pass="";//??????????????? dont save in plaintext
            return "success";
        }
        //TODO not logged in
        return "fail";
    }


    //something like this
    public messageBean getMessageBean() {
        return new messageBean(user);
    }

    public profileBean getProfileBean() {
        return new profileBean(user);
    }

    public searchBean getSearchBean() {
        return new searchBean(user);
    }

    public wallBean getWallBean() {
        return new wallBean(user);
    }


}
