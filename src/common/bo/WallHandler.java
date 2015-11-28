package common.bo;

import common.model.User;
import common.model.WallPost;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

/**
 * Created by sirena on 2015-11-18.
 */

public class WallHandler {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pres_comm");
    static EntityManager em;

    public static boolean post(long username, String postText) {
        boolean out = true;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            WallPost post = new WallPost();
            User u = UserHandler.getUser(username, em);
            post.setUser(u);
            post.setPost(postText);
            //users connection
            Collection<WallPost> posts = u.getWallPost();
            posts.add(post);
            u.setWallPost(posts);
            em.persist(post);
            em.merge(u);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            out = false;
        } finally {
            em.close();
            return out;
        }
    }

    public static Collection<WallPost> getPosts(long username) {
        em = emf.createEntityManager();
        User u = UserHandler.getUser(username, em);
        em.close();
        return u.getWallPost();
    }

    public static WallPost getPost(long id) {
        em = emf.createEntityManager();
        WallPost post= (WallPost) em.createNamedQuery("findPostById").setParameter("id", id).getSingleResult();
        em.close();
        return post;
    }

}
