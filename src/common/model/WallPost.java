package common.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sirena on 2015-11-12.
 */

@Entity
@Table(name = "wall_post")
public class WallPost implements Serializable{
    private static final long serialVersionUID = 1L;


    private long postId;
    private String post;
   // private long u_id;
    private User user;

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

    public void setPost(String post) {
        this.post = post;
    }

  /*  @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "u_id", unique = true, nullable = false)
    public long getU_id() {
        return u_id;
    }

    public void setU_id(long u_id) {
        this.u_id = u_id;
    }
*/
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="u_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
