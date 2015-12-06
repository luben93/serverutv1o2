import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

/**
 * Created by luben on 2015-11-03.
 */
public class Main {

    public static void main(final String[] args) throws NoSuchAlgorithmException {
        // use JUint testing in tester instead
        System.out.println("hej");
        Client cli = ClientBuilder.newClient();
        WebTarget target = cli.target("http://localhost:8081/rest/users/hejsan/1/lucas");
        Response out = target.request().get();
        System.out.println(out.readEntity(String.class));

    }

}
