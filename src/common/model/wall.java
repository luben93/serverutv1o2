package common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by luben on 2015-11-07.
 */
@Entity
@Table(name="wall")
public class Wall {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="u_id")
    private int user;
    @Column(name="message")
    private String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
