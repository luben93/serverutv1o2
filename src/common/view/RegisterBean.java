package common.view;


import common.viewModel.ViewUser;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

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
       /* try {
            if(UserHandler.register(new ViewUser(name,pass))) {
                return "home";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistExecption userAlreadyExistExecption) {
            userAlreadyExistExecption.printStackTrace();
            return "index";
        }*/
        //TODO rest all up in here
        //TODO not logged in

        ViewUser user = new ViewUser( name, pass);

        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/users/reg");
        Response resp = target.request().post(Entity.json(user));
        boolean tmp = resp.readEntity(Boolean.class);
        if(tmp){
            return "index";//success home eller index? lat?
        }
        return "index";
    }

}
