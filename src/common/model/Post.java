package common.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sirena on 2015-11-10.
 */
@Entity
@Table(name="post")
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    private long postId;
    private String post;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true,nullable = false)
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String message) {
        this.post = message;
    }
}
