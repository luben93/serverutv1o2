package common.view;

import common.model.UserAlreadyExistExecption;
import common.model.UserHandler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.security.NoSuchAlgorithmException;

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

    public String login(){

        UserHandler uh=new UserHandler();
        try {
            if(uh.register(name,pass)) {
                return "success";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistExecption userAlreadyExistExecption) {
            userAlreadyExistExecption.printStackTrace();
            return "fail";
        }
        //TODO not logged in
        return "fail";
    }

}
