package common.model;

/**
 * Created by luben on 2015-11-07.
 */
public class UserHandler {
   /* static Session sesh = HibUtil.getSessionFactory().openSession();
    public static boolean login(String username,String password){

        sesh.beginTransaction();
        List result = sesh.createQuery("from User where username='"+username+"' and password='"+cryptWithMD5(password)+"'").list();
        sesh.getTransaction().commit();
        System.out.println(result.get(0).toString());
        return true;
    }

    public static boolean register(String name,String pass) throws NoSuchAlgorithmException, UserAlreadyExistExecption {
        List existing=sesh.createQuery("from User where username='"+name+"'").list();
        if(existing.get(0)!=null){
            throw  new UserAlreadyExistExecption("user already exists");
        }
        sesh.beginTransaction();
        User u=new User();
        u.setUsername(name);
        u.setPassword(cryptWithMD5(pass));
        //u.setuId(autoIncr());
        sesh.save(u);
        sesh.getTransaction().commit();
        return true;
    }

    static int autoIncr(){

        List<Integer> result=sesh.createQuery("select max(uId) from Users").list();
        Integer userId=1;
        if(result.get(0)!=null){
            userId=result.get(0)+1;
        }
        return userId;
    }

    static String cryptWithMD5(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            //Logger.getLogger(CryptWithMD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;


    }*/

}

