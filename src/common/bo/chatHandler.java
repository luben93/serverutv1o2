package common.bo;

import common.model.ChatMessage;
import common.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by luben on 2015-11-28.
 */
public class chatHandler {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pres_comm");
    static EntityManager em;

    public static Collection<ChatMessage> getMessages(long sender, long reciver) {
        em = emf.createEntityManager();
        em.getTransaction().begin();//TODO ??????
        List<ChatMessage> out = em.createNamedQuery("getChat").setParameter("sender", sender).setParameter("receiver", reciver).getResultList();
        em.getTransaction().commit();
        em.close();
        return out;
    }

    public static boolean sendMessage(long sender,long reciver, String msgtxt){
        em = emf.createEntityManager();
        em.getTransaction().begin();
        ChatMessage msg = new ChatMessage();
        msg.setMessage(msgtxt);
        msg.setReceiver(reciver);
        msg.setSender(sender);
        em.persist(msg);
        em.getTransaction().commit();
        em.close();
        return true;
    }

}
