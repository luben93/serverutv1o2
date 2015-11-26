package common.view;

import common.bo.ProfileHandler;
import common.bo.UserHandler;
import common.model.Profile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by luben on 2015-11-07.
 */
@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class loginBean {


    private String name;
    private String pass;
//    private Profile profile;
    private String searchName;
    private String profileName;
    private int age;
    private String desc;

    public String getProfileName() {
        return profileName;
    }

    public int getAge() {
        return age;
    }

    public String getDesc() {
        return desc;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    public void setSearchName(String searchName){
        this.searchName=searchName;
    }

    public String getSearchName(){
        return searchName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    /* public Profile getProfile() throws IOException, ClassNotFoundException {
        return ProfileHandler.getProfile(name);//TODO ajabaja inte skicka model lager
                                                //TODO även om det är en kopia
    }*/

    public String update(){
        //TODO do save stuff here

        return "home";
    }

    public Collection getWall(){
        ArrayList<String> out= new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            out.add("Bacon ipsum dolor amet turkey strip steak t-bone chicken capicola. Short loin sirloin landjaeger andouille venison jerky doner ham hock tail turducken ribeye tongue. Pancetta beef ribs biltong cupim. Short loin shank jowl short ribs landjaeger ball tip pig prosciutto chicken. Shoulder sausage drumstick short ribs ham hock picanha rump kevin. Picanha sausage strip steak turkey kevin tri-tip.");
            out.add("Andouille cupim shoulder, sausage drumstick chicken rump pork chop chuck pork belly short ribs pancetta. Strip steak corned beef ball tip beef ribs short ribs cupim swine. Andouille pork belly ham hock beef ribs meatball picanha rump brisket cupim. Bacon jerky ball tip, meatloaf turkey meatball shank hamburger ground round pork chop spare ribs. Ground round landjaeger venison, hamburger short ribs kevin bacon pancetta shank chuck pig. Boudin salami tenderloin pancetta sirloin filet mignon kevin. Shank leberkas turducken pork belly filet mignon.");
            out.add("Frankfurter kielbasa doner bresaola t-bone, andouille sausage turducken pork pastrami. Bacon brisket landjaeger, ribeye tongue cow bresaola chicken shankle hamburger kielbasa tail. Short ribs venison tenderloin pork belly brisket, doner frankfurter landjaeger sirloin ball tip. Swine fatback tenderloin, beef ribs corned beef ribeye chuck sirloin porchetta short loin cow cupim. Venison meatloaf short ribs tongue, jowl pork chop alcatra bresaola ball tip porchetta landjaeger turkey meatball rump.");
            out.add("Frankfurter salami porchetta, drumstick landjaeger ball tip pork belly tri-tip ham tail. Ribeye shank chicken tongue turducken pancetta shankle meatloaf. Short ribs beef ground round meatloaf, pork belly tongue kielbasa cow drumstick pork flank. Ham hock turducken hamburger short ribs biltong t-bone chuck. Prosciutto biltong turducken leberkas brisket jerky turkey picanha pork loin pancetta. Swine ground round flank, short loin pancetta shank prosciutto frankfurter pig rump short ribs drumstick cow pork. Alcatra beef pork, landjaeger sirloin boudin chicken swine shankle rump bacon bresaola cupim pig.");
            out.add("Jowl pork belly boudin venison. Pork tenderloin picanha rump biltong shankle short ribs alcatra pig tongue fatback strip steak. Ribeye biltong pig bresaola short loin cow ham pork belly meatball pork. Kielbasa pork belly bacon picanha.");
        }
        return out;
    }

    public void setGender(String gender){
        if(gender.contains("female")){
           //is female
        }else{
            // is male

        }
    }

    public String getGender() throws IOException, ClassNotFoundException {
        if (ProfileHandler.getProfile(name).getIsFemale()){
            return "woman";
        }else{
            return "man";
        }
    }

    public Collection getResults() throws IOException, ClassNotFoundException {
        return ProfileHandler.search(searchName);//TODO fuuuuuuuu not safe at all, actuall list of all user with name
    }


    public String login() throws IOException, ClassNotFoundException  {
        if (UserHandler.login(name, pass)) {
            System.out.println("logged in");
            Profile tmp= ProfileHandler.getProfile(name);
            profileName=tmp.getName();
            age=tmp.getAge();
            desc=tmp.getDescription();
            return "home";
        }
        //TODO not logged in
        return "index";
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }

}
