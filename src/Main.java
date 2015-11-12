import common.HibUtil;
import common.model.User;
import common.model.WallPost;
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

        //Create user
     /*  User u=new User();

        Set<ChatMessage> chatmess = new HashSet<ChatMessage>();
        ChatMessage chm = new ChatMessage();
        chm.setMessage("cool");

        chatmess.add(chm);
        u.setMessages(chatmess);

        u.setUsername("cooling");
        u.setPassword(cryptWithMD5("secret"));

        //Create profile
        Profile p = new Profile();
        p.setAge(23);
        p.setDescription("Cool girl");
        //0 = woman, 1 = man
        p.setGender(0);
        p.setName("Sirena9392");
*/
        User u = (User) sesh.createQuery("from User p where p.u_id = 2").uniqueResult();

        WallPost wp = new WallPost();
        Collection<WallPost> wpc = new ArrayList<>();
        wp.setPost("oooook");
        wpc.add(wp);
        wp.setUser(u);
        u.setWallPost(wpc);

 /*

     /*   Wall w = new Wall();
        ArrayList<Post> messages = new ArrayList<>();
        Post m = new Post();
        m.setMessage("jullan owns");
        messages.add(m);
        w.setPosts(messages
        w.setUser(u);
        u.setWall(w);*/

       /* Wall w = new Wall();
        ArrayList<Post> messages = new ArrayList<>();
        Post m = new Post();
        m.setMessage("jullan owns");
        messages.add(m);
        w.setPosts(messages);
        p.setWall(w);
        w.setProfile(p);*/
       // User u2 = (User) sesh.createQuery("from User p where p.u_id = 162").uniqueResult();

      //  User u1 = (User) sesh.createQuery("from User p where p.u_id = 12").uniqueResult();
      //  Profile profile = (Profile) sesh.createQuery("from Profile p where p.u_id = 2").uniqueResult();
     /*   Wall w = new Wall(); //profile.se
        Post post = new Post();
        post.setPost("Jiullaneciol");
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);
        w.setPosts(posts);*/
     //   w.setUser(u);
       // u.setWall(w);

    /*    ChatMessage cm = new ChatMessage();
        cm.setMessage("noooooooo");
     //   cm.setUser(u);
        cm.setUser(u1);
        ArrayList<ChatMessage> cma = new ArrayList<>();
        cma.add(cm);

        u2.setMessages(cma);*/
    /*   Profile profile = (Profile) sesh.createQuery("from Profile p where p.u_id = 2").uniqueResult();

        Collection<Profile> pr = new ArrayList<>();
        pr.add(profile);
        p.setFollow(pr);*/



       // p.setFriendOf(pr);


     /*   p.setUser(u);
        u.setProfile(p);
     */

      //  Profile profile = (Profile) sesh.createQuery("from user mod where mod.moduleID = 1").uniqueResult();
/*
        ArrayList<ChatMessage> messagesChat = new ArrayList<>();

        ChatMessage cm = new ChatMessage();
        cm.setMessage("tjoo");
        messagesChat.add(cm);
        Profile f = new Profile();
        ArrayList<Profile> fr = new ArrayList<>();
        fr.add(f);
        cm.setTo(f);
        Wall w = new Wall();
        ArrayList<Post> m = new ArrayList<>();
        Post msg = new Post();
        msg.setMessage("hu");
        m.add(msg);
        msg = new Post();
        msg.setMessage("ha");
        m.add(msg);
        w.setPosts(m);
        w.setUser(u);
        u.setWall(w);
        u.setProfile(p);
        p.setFriends(fr);
        p.setPosts(messagesChat);
        p.setUser(u);*/
    //    sesh.flush();
        sesh.saveOrUpdate(u);
        sesh.saveOrUpdate(wp);
        //sesh.save(p);
        sesh.getTransaction().commit();
        sesh.close();
        /*
        Session s = HibUtil.openSession();
        s.beginTransaction();
        s.save(u);
        s.getTransaction().commit();
        s.close();

*/
         /*   sesh.beginTransaction();
            Users u=new Users();
            u.setUsername("Cooltjej");
            u.setPassword(cryptWithMD5("jullan"));
            sesh.save(u);
            sesh.getTransaction().commit();*/

          //  UserHandler.register("Julia","Thun");

      //  UserHandler.login("lucas","hemlis");

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
