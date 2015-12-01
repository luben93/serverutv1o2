import common.view.RegisterBean;
import common.view.loginBean;

import java.security.NoSuchAlgorithmException;

/**
 * Created by luben on 2015-11-03.
 */
public class Main {

    public static void main(final String[] args) throws NoSuchAlgorithmException {
        // use JUint testing in tester instead

            String n = "name";
            String p = "pass";
            RegisterBean r = new RegisterBean();
            r.setName(n);
            r.setPass(p);
            loginBean bean = new loginBean();
            bean.setName(n);
            bean.setPass(p);

            bean.login();
        }

}
