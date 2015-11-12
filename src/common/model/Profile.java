package common.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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

  //  private Collection<ChatMessage> FromMessages = new ArrayList<>();
    //private Collection<ChatMessage> ToMessages = new ArrayList<>();

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


   /* @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "from")
    public Collection<ChatMessage> getFromMessages() {
        return FromMessages;
    }

    public void setFromMessages(Collection<ChatMessage> FromMessages) {
        this.FromMessages = FromMessages;
    }*/

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

    //0 = woman, 1 = man
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


   /* @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    public Collection<ChatMessage> getToMessages() {
        return ToMessages;
    }

    public void setToMessages(Collection<ChatMessage> toMessages) {
        ToMessages = toMessages;
    }
*/
    // @OneToOne(fetch = FetchType.LAZY,mappedBy = "profile",cascade = CascadeType.ALL)

}