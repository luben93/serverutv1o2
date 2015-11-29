package common.model;
import javax.persistence.Embeddable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by sirena on 2015-11-10.
 */

@NamedQueries({
        @NamedQuery(
                name = "getChatMessageBetweenSenderAndReceiver",
                query = "from ChatMessage cm " +
                        "where (cm.sender = :sender and cm.receiver = :receiver) " +
                        "or (cm.sender = :receiver and cm.receiver = :sender)"
        )
})
@Embeddable
@Table(name="chatmessage")
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private long sender;
    private long receiver;

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long user) {
        this.sender = user;
    }


}
