package common.bo;

import common.model.ChatMessage;
import common.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

/**
 * Created by luben on 2015-11-28.
 */
public class chatHandler {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pres_comm");
    static EntityManager em;

    public static Collection<ChatMessage>getMessages(long id,long otherId){
        em=emf.createEntityManager();
        em.getTransaction().begin();//TODO ??????
        Collection<ChatMessage> out=em.createNamedQuery("getChatMessageBetweenIDandOhterId").setParameter(":id",id).setParameter(":OtherID",otherId).getResultList();
        em.getTransaction().commit();
        em.close();
        return out;
    }
}
