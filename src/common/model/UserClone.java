package common.model;

/**
 * Created by luben on 2015-11-12.
 */
public class UserClone extends User {
    private long u_id;
    private String username;
    private String password;

    public UserClone(User u){
        u_id=u.getU_id();
        username=u.getUsername();
        password=u.getPassword();
    }
}
