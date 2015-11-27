package common.bo;

import common.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

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
            User f = UserHandler.getUser(following,em);
            Collection<User> follow=u.getFollow();
            if(follow.contains(f)){
                out=false;
                em.getTransaction().rollback();
            }else {
                follow.add(f);
                f.getFollowed().add(u);
                em.merge(f);
                em.merge(u);
                em.getTransaction().commit();
            }
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            out=false;
        }finally {
            em.close();
            return out;
        }

    }

    public static Collection<User> getFollowers(String user){
        em = emf.createEntityManager();
        User u = UserHandler.getUser(user,em);
        Collection<User> out= u.getFollow();
        System.out.println(out);
        em.close();
        return out;
    }

    public static int countFollowing(String user){
        em = emf.createEntityManager();
        User u = UserHandler.getUser(user,em);
        // Collection<User> out= u.getFollowed();
        List out = em.createNativeQuery("" +
                "SELECT user.u_id,user.username " +
                "FROM tbl_friends " +
                "INNER JOIN user " +
                "ON user.u_id = tbl_friends.f_id " +
                "WHERE f_id =:fid").setParameter("fid",u.getU_id()).getResultList();
        em.close();

        return out.size();
    }

    public class custom{
        public long u_id;
        public String username;
    }

}
