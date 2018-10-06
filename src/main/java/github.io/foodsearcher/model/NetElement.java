package top.yaoyongdou.model;

import java.util.List;

/**
 * Created by AAAAA on 2018-02-19.
 */
public class NetElement {
    String type;
    List<NetEleInstance> elements;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NetEleInstance> getElements() {
        return elements;
    }

    public void setElements(List<NetEleInstance> elements) {
        this.elements = elements;
    }
}
