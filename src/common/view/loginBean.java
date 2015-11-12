package common.view;

import common.model.UserHandler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by luben on 2015-11-07.
 */
@ManagedBean(name="loginBean",eager=true)
@SessionScoped
public class loginBean {
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    private boolean isLoggedIn=false;
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

    public String login(){

        UserHandler uh=new UserHandler();
        if(uh.login(name,pass)) {
            System.out.println("logged in");
            isLoggedIn=true;
            return "success";
        }
        //TODO not logged in
        return "fail";
    }

}
