package common.bo;

import common.model.Profile;
import common.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.io.IOException;

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
