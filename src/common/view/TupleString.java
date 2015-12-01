package common.view;

/**
 * Created by luben on 2015-11-30.
 */
public class TupleString {
    private String key;
    private String value;

    public TupleString(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key+", "+value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
