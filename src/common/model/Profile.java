package common.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.mapping.List;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sirena on 2015-11-10.
 */
@Entity
@Table(name="profile")
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    private long u_id;
    private String name;
    private int age;
    private int gender;
    private User user;
    private String description;
    @ManyToMany
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    protected List friends = null;
   /* private Collection<Profile> friends = new ArrayList<>();
    private Collection<Profile> friendOf = new ArrayList<>();
    private Collection<ChatMessage> messages = new ArrayList<>();
*/

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    public long getU_id() {
        return u_id;
    }

    public void setU_id(long u_id) {
        this.u_id = u_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="u_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
/*
    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="f_id"),
            inverseJoinColumns=@JoinColumn(name="u_id")
    )
  //@OneToMany(fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
    public Collection<Profile> getFriends() {
        return friends;
    }

    public void setFriends(Collection<Profile> friends) {
        this.friends = friends;
    }

    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="f_id"),
            inverseJoinColumns=@JoinColumn(name="u_id")
    )
 // @OneToMany(fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
    public Collection<Profile> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(Collection<Profile> friendOf) {
        this.friendOf = friendOf;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "to",cascade = CascadeType.ALL)
    public Collection<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(Collection<ChatMessage> messages) {
        this.messages = messages;
    }
*/
}