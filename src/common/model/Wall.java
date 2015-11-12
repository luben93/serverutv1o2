package common.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sirena on 2015-11-10.
 */
@Entity
@Table(name="wall")
public class Wall implements Serializable {
    private static final long serialVersionUID = 1L;

    private long u_id;
    private User user;
    private Collection<Post> posts = new ArrayList<>();

    public Wall() {
    }

    @GenericGenerator(name = "generator", strategy = "foreign",
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

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="u_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
