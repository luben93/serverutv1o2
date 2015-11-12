package common.view.loggedIn;

import common.model.User;

import javax.faces.bean.ManagedBean;

/**
 * Created by luben on 2015-11-07.
 */

@ManagedBean(name="profileBean")
public class profileBean extends abstractUserBean {

    private String desc;
    private int age;
    private boolean isFemale;
    private String name;

    public String getDesc() {
        return desc;
    }

    public int getAge() {
        return age;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public String getName() {
        return name;
    }

    public profileBean(User u) {
        super(u);
    }

}
