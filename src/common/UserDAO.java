package common;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by luben on 2015-11-03.
 */
public class UserDAO {
    Session sesh = HibUtil.getSessionFactory().openSession();
    public void save(User u){
        sesh.beginTransaction();
        sesh.save(u);
        sesh.getTransaction().commit();
        sesh.close();
    }

    public Integer getId(){
        Query q=sesh.createQuery("select max(user.id) from User user");
        List<Integer> result=q.list();
        Integer userId=1;
        if(result.get(0)!=null){
            userId=result.get(0)+1;
        }
        return userId;
    }
}
