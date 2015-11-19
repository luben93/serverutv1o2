package common.bo;

import common.model.Profile;
import common.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

/**
 * Created by sirena on 2015-11-18.
 */
public class ProfileHandler{
    static SessionFactory seshF = HibUtil.getSessionFactory();

    public static Profile getProfile(String username) throws IOException, ClassNotFoundException {
        Session sesh=seshF.openSession();
        sesh.beginTransaction();
        User user = (User) sesh.createQuery("from User where username='"+username+"'").uniqueResult();
        sesh.getTransaction().commit();
        Profile p =user.getProfile();//får inte returnera
        sesh.close();
        return (Profile) Copy.clone(p);
    }

    static void setDefaultProfile(User u){
        Session sesh=seshF.openSession();
        sesh.beginTransaction();
        Profile p = new Profile();
        p.setAge(-1);
        p.setDescription("update description");
        p.setIsFemale(false);
        p.setName(u.getUsername());
        p.setUser(u);
        u.setProfile(p);
        sesh.save(p);
        sesh.saveOrUpdate(u);
        sesh.getTransaction().commit();
        sesh.close();
    }

}
