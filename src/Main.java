import common.model.HibUtil;
import org.hibernate.Session;

import java.security.NoSuchAlgorithmException;

/**
 * Created by luben on 2015-11-03.
 */
public class Main {
    static Session sesh = HibUtil.getSessionFactory().openSession();


    public static void main(final String[] args) throws NoSuchAlgorithmException {
            // use JUint testing in tester instead
    }

}
