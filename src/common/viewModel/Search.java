package common.viewModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by luben on 2015-12-06.
 */
@XmlRootElement
public class Search implements Serializable {
    private String search;
    private String exclude;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public Search() {

    }

    public Search(String search, String exclude) {

        this.search = search;
        this.exclude = exclude;
    }
}
