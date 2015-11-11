package common.model;

import javax.persistence.*;

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

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true,nullable = false)
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

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

}