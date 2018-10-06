package top.yaoyongdou.controller.util;

import io.fabric8.kubernetes.api.model.Node;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import top.yaoyongdou.controller.NodeController;
import top.yaoyongdou.model.NodeStatus;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by young on 18-3-6.
 */
public class NodeMap {

    final static public String noneNode = "noneNode";

    static public Map<String, NodeStatus> getNodeStatusMap() {
        KubernetesClient client = new DefaultKubernetesClient();
        List<Node> nodeList = client.nodes().list().getItems();

        HashMap<String, NodeStatus> nodeStatusMap = new HashMap<String, NodeStatus>();

        HashMap cpuNodeIp2UsageMap = NodeController.getIpPercentageMap("cpu");
        HashMap memNodeIp2UsageMap = NodeController.getIpPercentageMap("mem");
        HashMap netNodeIp2UsageMap = NodeController.getIpPercentageMap("net");
        for (Node node: nodeList) {
            NodeStatus nodeStatus = NodeController.getNodeStatus(node, cpuNodeIp2UsageMap, memNodeIp2UsageMap, netNodeIp2UsageMap);
            if (nodeStatus == null) {
                continue;
            }
            nodeStatusMap.put(node.getMetadata().getName(), nodeStatus);
        }

        // 调度失败的pods
        nodeStatusMap.put("noneNode", new NodeStatus(NodeMap.noneNode, "", null, 0.0, 0.0, 0.0));

        return nodeStatusMap;
    }
}
