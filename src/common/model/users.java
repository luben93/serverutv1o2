package common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by luben on 2015-11-07.
 */
@Entity
@Table(name="users")
public class Users {
    @Id
    @Column(name="u_id")
    private int uId;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
