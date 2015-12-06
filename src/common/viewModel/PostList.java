package common.viewModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luben on 2015-12-06.
 */
@XmlRootElement

public class PostList  implements Serializable {
    private List<post> list;

    public List<post> getList() {
        return list;
    }

    public void setList(List<post> list) {
        this.list = list;
    }

    public PostList() {

    }
}
