package top.yaoyongdou.model;

import java.util.List;

/**
 * Created by young on 18-3-6.
 */
public class NodeStatus {
    private String nodeName;
    private String nodeLocation;
    private List<String> elements;
    private double cpu;
    private double mem;
    private double net;

    public NodeStatus(String nodeName, String nodeLocation, List<String> elements, double cpu, double mem, double net) {
        this.nodeName = nodeName;
        this.nodeLocation = nodeLocation;
        this.elements = elements;
        this.cpu = cpu;
        this.mem = mem;
        this.net = net;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeLocation() {
        return nodeLocation;
    }

    public void setNodeLocation(String nodeLocation) {
        this.nodeLocation = nodeLocation;
    }

    public List<String> getElements() {
        return elements;
    }

    public void setElements(List<String> elements) {
        this.elements = elements;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public double getMem() {
        return mem;
    }

    public void setMem(double mem) {
        this.mem = mem;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }
}
