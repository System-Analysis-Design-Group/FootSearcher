package top.yaoyongdou.model;

import java.util.List;

/**
 * Created by young on 18-3-6.
 */
public class PodInstance {

    public static class PodInfo {
        private String podName;
        private String nodeName;
        private String status;
        private double cpu;
        private double mem;
        private double net;

        public PodInfo(String podName, String nodeName, String status, double cpu, double mem, double net) {
            this.podName = podName;
            this.nodeName = nodeName;
            this.status = status;
            this.cpu = cpu;
            this.mem = mem;
            this.net = net;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPodName() {
            return podName;
        }

        public void setPodName(String podName) {
            this.podName = podName;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
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

    private String type;
    List<PodInfo> elements;

    public PodInstance(String type, List<PodInfo> elements) {
        this.type = type;
        this.elements = elements;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PodInfo> getElements() {
        return elements;
    }

    public void setElements(List<PodInfo> elements) {
        this.elements = elements;
    }
}
