import common.model.UserHandler;

import java.security.NoSuchAlgorithmException;

/**
 * Created by luben on 2015-11-03.
 */
public class Main {


    public static void main(final String[] args) throws NoSuchAlgorithmException {

        UserHandler.register("lucas","hemlis");
        UserHandler.login("lucas","hemlis");

    }
}
