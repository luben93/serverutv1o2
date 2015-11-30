import common.view.RegisterBean;
import common.view.TupleString;
import common.view.loginBean;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by luben on 2015-11-12.
 */

public class tester extends TestCase {

    @Test
    public void testLogin(){
        assertTrue("home".equals(login().login()));
    }

    public loginBean login(){
       loginBean bean=new loginBean();
       bean.setPass("pass");
       bean.setName("name");
       String out = bean.login();

       return bean;
   }

    @Test
    public void testReg(){
        RegisterBean reg=new RegisterBean();
        reg.setName("name");
        reg.setPass("pass");
        assertTrue("home".equals(reg.register()));
    }

    @Test
    public void testHome(){
        loginBean bean = login();
        bean.setSearchName("me");
        bean.addFriend("2");
        bean.showProfile("2");
        bean.postToWall();
        bean.update();
        for (TupleString ts:bean.getResults()) {
            System.out.println(ts.getKey());
            ts.getValue();
        }
        bean.getMessages();
        bean.setMsg("hej");
        bean.setId(Long.valueOf(2));
        bean.sendMessage();
//        bean.logout();

    }

}
