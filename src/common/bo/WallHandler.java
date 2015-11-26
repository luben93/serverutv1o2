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

    public static boolean post(String username, String postText) {
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
        } catch (Exception e) {
            out = false;
        } finally {
            em.getTransaction().commit();
            em.close();
            return out;
        }
    }

    public static Collection<WallPost> getPosts(String username) {
        em = emf.createEntityManager();
        User u = UserHandler.getUser(username, em);
        return u.getWallPost();
    }

    public static WallPost getPost(int id) {
        em = emf.createEntityManager();
        return (WallPost) em.createNamedQuery("findPostById").setParameter("id", id).getSingleResult();
    }

}