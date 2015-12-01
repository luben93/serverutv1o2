package common.view;

/**
 * Created by luben on 2015-11-19.
 */
public class SimpleUser {
    private String name;
    private long id;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public SimpleUser(String name, long id) {

        this.name = name;
        this.id = id;
    }

    @Override
    public String toString(){
        return name;
    }
}
