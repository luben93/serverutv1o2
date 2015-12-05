package common.viewModel;

/**
 * Created by luben on 2015-12-05.
 */
public class profile {
    private long uid;
    private String name;
    private int age;
    private boolean isFemale;
    private String desc;

    public profile(String name) {
        this.name = name;
    }

    public long getUid() {

        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale(boolean female) {
        isFemale = female;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public profile(long uid, String name, int age, boolean isFemale, String desc) {

        this.uid = uid;
        this.name = name;
        this.age = age;
        this.isFemale = isFemale;
        this.desc = desc;
    }
}
