package common.bo;

import common.model.User;
import org.hibernate.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by luben on 2015-11-07.
 */
public class UserHandler {
    static Session sesh = HibUtil.getSessionFactory().openSession();

    public static boolean login(String username,String password){

        sesh.beginTransaction();
        User user = (User) sesh.createQuery("from User where username='"+username+"' and password='"+cryptWithMD5(password)+"'").uniqueResult();
        sesh.getTransaction().commit();
        if(user!=null) {
            return true;
        }
        return false;
    }

    public static boolean register(String name,String pass) throws NoSuchAlgorithmException, UserAlreadyExistExecption {
        User existing=(User) sesh.createQuery("from User where username='"+name+"'").uniqueResult();
        if(existing!=null){
            throw  new UserAlreadyExistExecption("user already exists");
        }
        sesh.beginTransaction();
        User user=new User();
        user.setUsername(name);//TODO check email
        user.setPassword(cryptWithMD5(pass));

        sesh.save(user);
        sesh.getTransaction().commit();

        ProfileHandler.setDefaultProfile(user);
        return true;
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

