package common.model;

import common.HibUtil;
import org.hibernate.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by luben on 2015-11-07.
 */
public class UserHandler {
    static Session sesh = HibUtil.getSessionFactory().openSession();
    public static boolean login(String username,String password){

        sesh.beginTransaction();
        List result = sesh.createQuery("from Users where username='"+username+"' and password='"+cryptWithMD5(password)+"'").list();
        sesh.getTransaction().commit();
        System.out.println(result.toString());
        return true;
    }

    public static boolean register(String name,String pass) throws NoSuchAlgorithmException {
        sesh.beginTransaction();
        Users u=new Users();
        u.setUsername(name);
        u.setPassword(cryptWithMD5(pass));
        sesh.save(u);
        sesh.getTransaction().commit();
        return true;
    }


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

