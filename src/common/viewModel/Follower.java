package common.viewModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by luben on 2015-12-05.
 */
@XmlRootElement

public class Follower implements Serializable {
    private long me;
    private long following;

    public Follower() {
    }

    public long getMe() {
        return me;
    }

    public long getFollowing() {
        return following;
    }

    public Follower(long me, long following) {

        this.me = me;
        this.following = following;
    }


}
