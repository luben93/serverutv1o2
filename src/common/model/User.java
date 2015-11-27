package common.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by sirena on 2015-11-10.
 */


@NamedQueries({
        @NamedQuery(
                name = "findUserByUsername",
                query = "from User u where u.username = :name"
        ),
        @NamedQuery(
        name = "findUserByUsernamePassword",
        query = "from User u where u.username = :name and u.password= :password"
)
})
@Entity
@Table(name="user")
public class User implements Serializable{
    private long u_id;
    private String username;
    private String password;
    private Profile profile;
    private Collection<WallPost> wallPost;
    private Set<ChatMessage> messages;
    private Collection<User> followed = new ArrayList<>();
    private Collection<User> follow = new ArrayList<>();

    public User(){

    }
    public User(long u_id){
        this.u_id = u_id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="u_id", unique=true,nullable = false)
    public long getU_id() {
        return u_id;
    }

    public void setU_id(long u_id) {
        this.u_id = u_id;
    }

    @Column(name="username", unique=true,nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne(cascade=CascadeType.ALL, mappedBy = "user")
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="f_id"),
            inverseJoinColumns=@JoinColumn(name="u_id")
    )
    public Collection<User> getFollowed() {
        return followed;
    }

    public void setFollowed(Collection<User> followed) {
        this.followed = followed;
    }

    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="u_id"),
            inverseJoinColumns=@JoinColumn(name="f_id")
    )
    public Collection<User> getFollow() {
        return follow;
    }

    public void setFollow(Collection<User> follow) {
        this.follow = follow;
    }

    @ElementCollection(targetClass=ChatMessage.class,fetch=FetchType.EAGER)
    @JoinTable (name = "chatmessage", joinColumns = @JoinColumn(name="User_ID"))
    public Set<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(Set<ChatMessage> messages) {
        this.messages = messages;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "user")
    public Collection<WallPost> getWallPost() {
        return wallPost;
    }

    public void setWallPost(Collection<WallPost> wallPost) {
        this.wallPost = wallPost;
    }
}