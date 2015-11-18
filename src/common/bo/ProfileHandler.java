package common.bo;

import common.model.Profile;
import common.model.User;
import org.hibernate.Session;

/**
 * Created by sirena on 2015-11-18.
 */
public class ProfileHandler{
    static Session sesh = HibUtil.getSessionFactory().openSession();

    public static Profile getProfile(String username){
        sesh.beginTransaction();
        User user = (User) sesh.createQuery("from User where username='"+username+"'").uniqueResult();
        sesh.getTransaction().commit();
        return user.getProfile();
    }

    static void setDefaultProfile(User u){
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
    }

}
