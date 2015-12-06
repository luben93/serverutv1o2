package common.viewModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luben on 2015-12-06.
 */
@XmlRootElement

public class MessageList implements Serializable {
    private List<message> list;

    public List<message> getList() {
        return list;
    }

    public void setList(List<message> list) {
        this.list = list;
    }

    public MessageList() {

    }
}
