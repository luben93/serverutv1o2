package common.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sirena on 2015-11-10.
 */
@Entity
@Table(name="user")
public class User{
    private long u_id;
    private String username;
    private String password;
    private Profile profile;
    private Wall wall;
    private Collection<ChatMessage> messages = new ArrayList<>();

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
    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }
    @OneToOne(cascade=CascadeType.ALL, mappedBy = "user")
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /*
    @OneToMany(mappedBy = "user",fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
   /* @JoinTable(name="chat_table",
            joinColumns={@JoinColumn(name="a_id")},
            inverseJoinColumns={@JoinColumn(name="b_id")}
    )*/
    /*
    public Set<ChatMessage> getPosts() {
        return messages;
    }

    public void setPosts(Collection<ChatMessage> messages) {
        this.messages = messages;
    }
    */
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "user")
   /* @JoinTable(name="chat_table",
            joinColumns={@JoinColumn(name="a_id")},
            inverseJoinColumns={@JoinColumn(name="b_id")}
    )*/
    public Collection<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(Collection<ChatMessage> messages) {
        this.messages = messages;
    }
}