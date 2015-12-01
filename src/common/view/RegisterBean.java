package common.view;


import com.google.gson.Gson;
import common.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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

        //success=UserHandler.register(name,pass)
        //TODO registrera

        Client cli;
        cli = ClientBuilder.newClient();
        //    Client cli = ClientBuilder.newClient();
        WebTarget trgt =cli.target("http://130.237.84.10:8081/starter/rest/register");
        Gson gson = new Gson();
        User user = new User();
        user.setPassword(pass);
        user.setUsername(name);
        String json=trgt.request(MediaType.APPLICATION_JSON).post(Entity.entity(gson.toJson(user),MediaType.APPLICATION_JSON),String.class);
        user = gson.fromJson(json,User.class);


        System.out.println("registrear" + name);
            if(user != null) {
                return "home";
            }

        //TODO not logged in
        return "index";
    }

}
