package common.bo;

import common.model.Profile;
import common.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sirena on 2015-11-18.
 */
public class ProfileHandler{

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pres_comm");
    static EntityManager em;
   // static SessionFactory seshF = HibUtil.getSessionFactory();

    public static Profile getProfile(String username) throws IOException, ClassNotFoundException {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        User existing = null;
        try {
            existing = (User) em.createNamedQuery("findUserByUsername")
                    .setParameter("name", username).getSingleResult();
        }catch (NullPointerException e){
            System.out.printf("The user do not exist");
        }catch (NoResultException e){
            System.out.printf("The user do not exist");
        }
        if(existing!=null){
            em.persist(existing);
            em.getTransaction().commit();
            Profile p = existing.getProfile();
            em.close();
            return (Profile) Copy.clone(p);
        }
        return new Profile();

    }

    public static boolean update(String name,int age,String desc,boolean isFemale,String username){
        em=emf.createEntityManager();
        em.getTransaction().begin();
        User u=UserHandler.getUser(username);
        Profile p=u.getProfile();
        p.setAge(age);
        p.setDescription(desc);
        p.setIsFemale(isFemale);
        p.setName(name);
        //p.setUser(u);
        //em.persist(p);
        em.merge(p);
        em.getTransaction().commit();
        em.close();

        return true;
    }

    public static Collection search(String name) throws IOException, ClassNotFoundException {
        Collection out;//= new ArrayList<SimpleUser>();
        try {
            em = emf.createEntityManager();
            out = em.createNamedQuery("findUserByUsernameContains").setParameter("name", "%"+name+"%").getResultList();
        } catch (NoResultException e) {
            out = new ArrayList<String>();
            out.add("no user found");
        }finally {
            em.close();
        }
        return out;
    }

        static void setDefaultProfile(User u, EntityManager em){
        Profile p = new Profile();
        p.setAge(-1);
        p.setDescription("update description");
        p.setIsFemale(false);
        p.setName(u.getUsername());
        p.setUser(u);
        u.setProfile(p);
     //   em.refresh(p);
     //   em.merge(p);
        em.persist(p);
        em.persist(u);
       // em.detach(u);
       // em.refresh(u);
        em.getTransaction().commit();
        em.close();
    }

}
