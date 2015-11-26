package common.bo;

import common.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by luben on 2015-11-07.
 */
public class UserHandler {
    //static SessionFactory seshF = HibUtil.getSessionFactory();

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pres_comm");
    static EntityManager em;

    public static boolean login(String username, String password) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        User existing = null;
        try {
            existing = (User) em.createNamedQuery("findUserByUsernamePassword")
                    .setParameter("name", username).setParameter("password", cryptWithMD5(password)).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }

        if (existing != null) {
            return true;
        }
        em.getTransaction().commit();
        em.close();
        return false;
    }

    static User getUser(String name,EntityManager lem){
        User out=(User) lem.createNamedQuery("findUserByUsername")
                .setParameter("name", name).getSingleResult();
        return out;

    }

    public static boolean register(String name, String pass) throws NoSuchAlgorithmException, UserAlreadyExistExecption {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        User existing = null;
        try {
            existing = (User) em.createNamedQuery("findUserByUsername")
                    .setParameter("name", name).getSingleResult();

        } catch (NoResultException e1) {

            User user = new User();
            user.setUsername(name);//TODO check email
            user.setPassword(cryptWithMD5(pass));
            ProfileHandler.setDefaultProfile(user, em);
            em.persist(user);
            // em.detach(u);
            // em.refresh(u);
            em.getTransaction().commit();
            em.close();
        }
        if (existing != null) {
            throw new UserAlreadyExistExecption("user already exists");
        }

        return true;
    }

    static String cryptWithMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            //Logger.getLogger(CryptWithMD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;


    }

}

