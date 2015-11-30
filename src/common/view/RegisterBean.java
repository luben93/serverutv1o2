package common.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by luben on 2015-11-07.
 */
@ManagedBean(name="RegisterBean",eager=true)
@RequestScoped
public class RegisterBean {
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

    public String register(){
        boolean success=true;
        //success=UserHandler.register(name,pass)
        //TODO registrera
        System.out.println("registrear" + name);
            if(success) {
                return "home";
            }

        //TODO not logged in
        return "index";
    }

}
