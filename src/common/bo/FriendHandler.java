package common.bo;

import common.model.User;
import common.viewModel.Follower;
import common.viewModel.profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by luben on 2015-11-26.
 */
public class FriendHandler {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pres_comm");
    static EntityManager em;

    public static boolean addFollower(Follower a){
        boolean out=true;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            User u = UserHandler.getUser(a.getMe(),em);
           // TypedQuery<User> query = em.createQuery(
            //        "SELECT new User(c.u_id) from User c where c.username = :following", User.class).setParameter("following",a.getFollowing());
            //User tmp = query.getSingleResult();
            User f = UserHandler.getUser(a.getFollowing(),em);
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

    public static Collection<profile> getFollowers(long user){//TODO id only
        em = emf.createEntityManager();
        User me = UserHandler.getUser(user,em);
        Collection<User> out= me.getFollow();

        ArrayList<profile> followers= new ArrayList<>();
        for (User u:out) {
            followers.add(new profile(u.getU_id(), u.getProfile().getName(), u.getProfile().getAge(), u.getProfile().getIsFemale(), u.getProfile().getDescription()));
        }
        System.out.println(out);
        em.close();
        return followers;
    }

    public static int countFollowing(long user){//TODO id only
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
//
//    public class custom{
//        public long u_id;
//        public String username;
//    }

}
