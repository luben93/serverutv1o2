package common.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sirena on 2015-11-10.
 */
@Entity
@Table(name="message")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private long messageId;
    private String message;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true,nullable = false)
    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
