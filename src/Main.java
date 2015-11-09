import common.model.UserAlreadyExistExecption;
import common.model.UserHandler;

import java.security.NoSuchAlgorithmException;

/**
 * Created by luben on 2015-11-03.
 */
public class Main {


    public static void main(final String[] args) throws NoSuchAlgorithmException {

        try {
            UserHandler.register("hej","hemlis");
        } catch (UserAlreadyExistExecption userAlreadyExistExecption) {
            userAlreadyExistExecption.printStackTrace();
        }
        UserHandler.login("lucas","hemlis");

    }
}
