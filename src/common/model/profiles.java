package common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by luben on 2015-11-07.
 */
@Entity
@Table(name = "profiles")
public class Profiles {

    @Column(name = "u_id")
    private int uId;
    @Column(name = "profile_object")
    private String profileObject;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getProfileObject() {
        return profileObject;
    }

    public void setProfileObject(String profileObject) {
        this.profileObject = profileObject;
    }
}
