package top.yaoyongdou.controller;

import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeAddress;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yaoyongdou.model.NodeStatus;
import top.yaoyongdou.service.BaseStationService;
import top.yaoyongdou.model.BaseStation;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Young on 2018-02-19.
 * 获取 node 运行信息
 */
@RestController
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*")
public class NodeController {

    @Autowired
    BaseStationService baseStationService;

    @RequestMapping("/nodes")
//    @RequiresPermissions("userInfo:view")
    @RequiresAuthentication
    public List<NodeStatus> getNodeStatus() {
        KubernetesClient client = new DefaultKubernetesClient();

        List<Node> nodeList = client.nodes().list().getItems();
        List<NodeStatus> nodeStatusList = new LinkedList<>();

        HashMap cpuNodeIp2UsageMap = getIpPercentageMap("cpu");
        HashMap memNodeIp2UsageMap = getIpPercentageMap("mem");
        HashMap netNodeIp2UsageMap = getIpPercentageMap("net");

        for (Node node : nodeList) {
            NodeStatus nodeStatus = getNodeStatus(node, cpuNodeIp2UsageMap, memNodeIp2UsageMap, netNodeIp2UsageMap);
            if (nodeStatus == null) {
                continue;
            }
            nodeStatusList.add(nodeStatus);
        }

        return nodeStatusList;
    }

    public static NodeStatus getNodeStatus(Node node,
                                           HashMap cpuNodeIp2UsageMap,
                                           HashMap memNodeIp2UsageMap,
                                           HashMap netNodeIp2UsageMap) {
        String nodeName = node.getMetadata().getName();
        List<NodeAddress> addresses = node.getStatus().getAddresses();
        String nodeip = null;
        for (NodeAddress addr : addresses) {
            if ("InternalIP".equals(addr.getType())) {
                nodeip = addr.getAddress();
                break;
            }
            System.out.println("get address...");
        }

        // 去掉主节点
        if ("k8s-ms".equals(nodeName)) {
            return null;
        }

        String nodeLocation = node.getMetadata().getAnnotations().get("locationName").toString();

        // TODO: 18-3-6
        List<String> eles = new LinkedList<String>();
        eles.add("mme");
        eles.add("hss");

        double cpu = 0.0;
        double mem = 0.0;
        double net = 0.0;
        if (cpuNodeIp2UsageMap.get(nodeip) != null) {
            cpu = (Double) cpuNodeIp2UsageMap.get(nodeip);
        }
        if (memNodeIp2UsageMap.get(nodeip) != null) {
            mem = (Double) memNodeIp2UsageMap.get(nodeip);
        }
        if (netNodeIp2UsageMap.get(nodeip) != null) {
            net = (Double) netNodeIp2UsageMap.get(nodeip);
        }

        NodeStatus nodeStatus = new NodeStatus(nodeName, nodeLocation, eles, cpu, mem, net);
        return nodeStatus;
    }


    @RequestMapping("/nodes/location")
    public List<BaseStation> getNodeLocation() {

        return baseStationService.findAllNode();
    }


//    @RequestMapping(value = "/nodes/statistic", produces="application/json")
    public static HashMap getIpPercentageMap(@RequestParam String type) {

        String usage = request4Usage(type);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode cpuJsonNode = null;
        try {
            cpuJsonNode = mapper.readValue(usage, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (!"success".equals(cpuJsonNode.get("status").getTextValue())) {
            return null;
        }

        HashMap node_usage  = new HashMap();
        ArrayNode array = (ArrayNode) cpuJsonNode.get("data").get("result");
        if (array.size() == 0) {
            return null;
        }
        for (JsonNode node : array) {
            String node_ip = node.get("metric").get("instance").getTextValue().split(":")[0];
            double usageDouble = Double.parseDouble(node.get("value").get(1).getTextValue());
            node_usage.put(node_ip, usageDouble);
        }

        return node_usage;
    }

    private static final String PROMETHEUS_SERVER_QUERY_URL = "http://114.67.37.2:10581/api/v1/query?query=";
    private static final String CPU_USAGE_QEURY = PROMETHEUS_SERVER_QUERY_URL + "100-(avg+by(instance)(irate(node_cpu%7Bjob%3D\"kubernetes-service-endpoints\"%2Cmode%3D\"idle\"%7D%5B1m%5D))*100)";
    private static final String MEMORY_USAGE_QUERY = PROMETHEUS_SERVER_QUERY_URL + "100-(node_memory_MemAvailable%2Fnode_memory_MemTotal*100)";
    private static final String NET_USAGE_QUERY =  PROMETHEUS_SERVER_QUERY_URL + "idelta(node_netstat_Tcp_InErrs%5B1m%5D)%2Fidelta(node_netstat_Tcp_InSegs%5B1m%5D)*100";
    /**
     *
     * @param type cpu, mem, net
     * @return
     */
    public static String request4Usage(String type) {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = null;

        if ("cpu".equals(type)) {
            getMethod = new GetMethod(CPU_USAGE_QEURY);
        }
        else if("mem".equals(type)) {
            getMethod = new GetMethod(MEMORY_USAGE_QUERY);
        }
        else if ("net".equals(type)) {
            getMethod = new GetMethod(NET_USAGE_QUERY);
        }
        else {
            return null;
        }

        String jsonData = null;
        try {
            httpClient.executeMethod(getMethod);
            jsonData = getMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonData;
    }
}
