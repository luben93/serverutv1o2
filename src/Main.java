import common.HibUtil;
import common.model.Profile;
import common.model.User;
import org.hibernate.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by luben on 2015-11-03.
 */
public class Main {
    static Session sesh = HibUtil.getSessionFactory().openSession();


    public static void main(final String[] args) throws NoSuchAlgorithmException {
        sesh.beginTransaction();
        User u=new User();
        u.setUsername("llklk");
        u.setPassword("pappa");

        Profile p = new Profile();
        p.setAge(54);
        p.setDescription("dfsd");
        p.setGender(1);
        p.setName("dsafdsf");
        //Profile profile = (Profile) sesh.createQuery("from Profile p where p.u_id = 112").uniqueResult();


      //  ArrayList<Profile> pr = new ArrayList<>();
        //pr.add(profile);
       // p.setFriends(pr);
       // p.setFriendOf(pr);
        p.setUser(u);
        u.setProfile(p);
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
        ArrayList<Message> m = new ArrayList<>();
        Message msg = new Message();
        msg.setMessage("hu");
        m.add(msg);
        msg = new Message();
        msg.setMessage("ha");
        m.add(msg);
        w.setMessages(m);
        w.setUser(u);
        u.setWall(w);
        u.setProfile(p);
        p.setFriends(fr);
        p.setMessages(messagesChat);
        p.setUser(u);*/
        sesh.flush();
        sesh.save(u);
        sesh.save(p);
        sesh.getTransaction().commit();
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
