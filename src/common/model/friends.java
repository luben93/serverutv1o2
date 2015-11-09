package common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by luben on 2015-11-07.
 */
@Entity
@Table(name="friends")
public class Friends {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="follower")
    private int follower;
    @Column(name="following")
    private int following;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }
}
