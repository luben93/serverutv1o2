package common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by luben on 2015-11-03.
 */

@ManagedBean(name="userBean",eager=true)
@RequestScoped
public class UserBean {
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private Integer age;

    public String saveUser(){
        System.out.println("n"+name+" a"+age);
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }
        UserDAO dao=new UserDAO();
        Integer userId=dao.getId();
        UserTmp user = new UserTmp(userId,name,age);
        dao.save(user);
        System.out.println("Saved");
        return "out";
    }
}
