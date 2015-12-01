package common.view;


import com.google.gson.Gson;
import common.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.*;
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

      /*  ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(getBaseURI());
        Employee employee = new Employee();
        employee.setEmployeeId(3);
        employee.setEmployeeName("srinivas");*/

        Client cli;
        cli = ClientBuilder.newClient();
        //    Client cli = ClientBuilder.newClient();
        WebTarget trgt =cli.target("http://130.237.84.10:8081/ServerutvLabb2-master/rest");
        Gson gson = new Gson();
        User user = new User();
        user.setPassword(pass);
        user.setUsername(name);
        String json;
        Invocation.Builder trgtToPost = trgt.path("/user/register").request(MediaType.TEXT_PLAIN);
        json= trgtToPost.post(Entity.entity(gson.toJson(user),MediaType.TEXT_PLAIN),String.class);
        user = gson.fromJson(json,User.class);


        System.out.println("registrear" + name);
            if(user != null) {
                return "home";
            }

        //TODO not logged in
        return "index";
    }

}
