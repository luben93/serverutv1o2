package common.bo;

import common.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Created by luben on 2015-11-26.
 */
public class FriendHandler {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pres_comm");
    static EntityManager em;

    public static boolean addFollower(String followerName,String following){
        boolean out=true;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            User u = UserHandler.getUser(followerName,em);
            TypedQuery<User> query = em.createQuery(
                    "SELECT new User(c.u_id) from User c where c.username = :following", User.class).setParameter("following",following);
            User tmp = query.getSingleResult();
            User f = new User();
            f.setU_id(tmp.getU_id());
            f.setUsername(following);
            f.setPassword("");
            Collection<User> follow=u.getFollow();
            follow.add(f);
            em.merge(u);
        }catch (Exception e){
            e.printStackTrace();
            out=false;
        }finally {
            em.getTransaction().commit();
            em.close();
            return out;
        }

    }

    public static Collection<User> getFollowers(String user){
        em = emf.createEntityManager();
        User u = UserHandler.getUser(user,em);
        Collection<User> out= u.getFollow();
        em.close();
        return out;
    }

    public static Collection<User> getFollowing(String user){
        em = emf.createEntityManager();
        User u = UserHandler.getUser(user,em);
        Collection<User> out= u.getFollowed();
        em.close();
        return out;
    }

    public class custom{
        public long u_id;
        public String username;
    }

}
