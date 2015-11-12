import common.model.HibUtil;
import common.model.Profile;
import common.model.User;
import org.hibernate.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by luben on 2015-11-03.
 */
public class Main {
    static Session sesh = HibUtil.getSessionFactory().openSession();


    public static void main(final String[] args) throws NoSuchAlgorithmException {
        sesh.beginTransaction();
/*
        User u = new User();
        u.setUsername("lucassugerskit");
        u.setPassword(cryptWithMD5("hemligt"));

        Profile p = new Profile();
        p.setAge(22);
        p.setDescription("Hej jag suger skit");
        p.setIsFemale(false);
        p.setName("lucas_93");
        p.setUser(u);

        u.setProfile(p);

        Collection<WallPost> wpc = new ArrayList<>();
        WallPost wp = new WallPost();
        wp.setPost(" Jag owns everone ");
        wp.setUser(u);
        wpc.add(wp);
        u.setWallPost(wpc);

        sesh.saveOrUpdate(u);
        sesh.saveOrUpdate(p);
        sesh.saveOrUpdate(wp);*/


        User u1 = new User();
        u1.setUsername("jullanowns");
        u1.setPassword(cryptWithMD5("hemligt"));
        Profile p1 = new Profile();
        p1.setAge(22);
        p1.setDescription("Hej jag suger inte skit");
        p1.setIsFemale(false);
        p1.setName("sirena_93");
        p1.setUser(u1);
        u1.setProfile(p1);
        Collection<User> c = new ArrayList<>();
        User u = (User) sesh.createQuery("from User where u_id = 2").uniqueResult();
        c.add(u);
        u1.setFollow(c);
        sesh.saveOrUpdate(u1);
        sesh.saveOrUpdate(p1);
      //  sesh.saveOrUpdate(wp);
        sesh.getTransaction().commit();

        sesh.close();


    }

    public static String cryptWithMD5(String pass){
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
