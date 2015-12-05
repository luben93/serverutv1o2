package common.view;

import common.bo.UserAlreadyExistExecption;
import common.bo.UserHandler;
import common.viewModel.ViewUser;

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
        try {
            if(UserHandler.register(new ViewUser(name,pass))) {
                return "home";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistExecption userAlreadyExistExecption) {
            userAlreadyExistExecption.printStackTrace();
            return "index";
        }
        //TODO not logged in
        return "index";
    }

}
