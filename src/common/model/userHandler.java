package common.model;

import common.HibUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by luben on 2015-11-07.
 */
public class UserHandler {
    static Session sesh = HibUtil.getSessionFactory().openSession();
    public static boolean login(String username,String password){

        sesh.beginTransaction();
        List result = sesh.createQuery("from Users where username='"+username+"' and password='"+password+"'").list();
        sesh.getTransaction().commit();
        System.out.println(result.toString());
        return true;
    }



}

