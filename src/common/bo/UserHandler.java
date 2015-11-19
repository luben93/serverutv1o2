package common.bo;

import common.model.User;
import common.view.SimpleUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by luben on 2015-11-07.
 */
public class UserHandler {
    //static SessionFactory seshF = HibUtil.getSessionFactory();

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pres_comm");
    static EntityManager em;

    public static boolean login(String username,String password){



       /* Session sesh=seshF.openSession();
        sesh.beginTransaction();
        User user = (User) sesh.createQuery("from User where username='"+username+"' and password='"+cryptWithMD5(password)+"'").uniqueResult();
        sesh.getTransaction().commit();
        if(user!=null) {
            return true;
        }*/
        return false;
    }

    public static boolean register(String name,String pass) throws NoSuchAlgorithmException, UserAlreadyExistExecption {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        //Session sesh=seshF.openSession();
        User existing = null;
        try {
            existing  =(User) em.createNamedQuery("findUserByUsername")
                    .setParameter("name", name).getSingleResult();

        }catch (NoResultException e){

        }
         //User existing=(User) sesh.createQuery("from User where username='"+name+"'").uniqueResult();
        if(existing!=null){
            throw  new UserAlreadyExistExecption("user already exists");
        }
     //   sesh.beginTransaction();
        User user=new User();
        user.setUsername(name);//TODO check email
        user.setPassword(cryptWithMD5(pass));
        em.persist(user);
        em.getTransaction().commit();
        em.close();
       /* sesh.save(user);
        sesh.getTransaction().commit();
*/
        ProfileHandler.setDefaultProfile(user);
        return true;
    }

    public static Collection search(String name) throws IOException, ClassNotFoundException {
   /*     Session sesh=seshF.openSession();
        Collection out= new ArrayList<SimpleUser>();
        Collection<User> res=sesh.createQuery("from User where username like '%"+name+"%'").list();
        for (User u: res) {
            out.add(new SimpleUser(u.getUsername(),u.getU_id()));
        }
        sesh.close();
        return out;*/
        Collection out= new ArrayList<SimpleUser>();
        return out;
    }
/*
    static int autoIncr(){

        List<Integer> result=sesh.createQuery("select max(uId) from Users").list();
        Integer userId=1;
        if(result.get(0)!=null){
            userId=result.get(0)+1;
        }
        return userId;
    }
    */

    static String cryptWithMD5(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
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

