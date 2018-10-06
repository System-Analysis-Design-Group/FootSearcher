package top.yaoyongdou.controller;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.extensions.Deployment;
import io.fabric8.kubernetes.api.model.extensions.DeploymentSpec;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;
import top.yaoyongdou.model.StatusMsg;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by young on 18-3-6.
 */

@RestController
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*")
public class ScheduleController {

    @RequestMapping(value = "/pods/schedule", method = RequestMethod.POST)
//    @RequiresAuthentication
    public StatusMsg scheduleManual(@RequestParam("podName") String podName,
                                    @RequestParam("nodeLabel") String nodeLabel) {
        KubernetesClient client = new DefaultKubernetesClient();

        String deploymentName = getDeploymentNameFromPodName(podName);
        if (deploymentName == null || "".equals(deploymentName)) {
            return StatusMsg.returnError();
        }

        Deployment deployment = client.extensions().deployments().inNamespace("default")
                .withName(deploymentName).get();

        if (deployment == null) {
            return StatusMsg.returnError();
        }

        //解构
        DeploymentSpec deploymentSpec = deployment.getSpec();
        PodTemplateSpec deploymentTemp = deploymentSpec.getTemplate();
        PodSpec podSpec = deploymentTemp.getSpec();
        ObjectMeta meta = deployment.getMetadata();

        //更新
        Map<String, String> nodeSelector = null;
        if (podSpec.getNodeSelector() == null) {
            nodeSelector = new HashMap<String, String>();
        } else {
            nodeSelector = podSpec.getNodeSelector();
        }
        nodeSelector.put("nodeLabel", nodeLabel);

        meta.setResourceVersion(null); // k8s demand resourceVersion to be null

        //重构
        podSpec.setNodeSelector(nodeSelector);
        deploymentTemp.setSpec(podSpec);
        deploymentSpec.setTemplate(deploymentTemp);
        deployment.setSpec(deploymentSpec);
        deployment.setMetadata(meta);

        client.extensions().deployments().delete(deployment);
        client.extensions().deployments().createOrReplace(deployment);

        return StatusMsg.returnOk();
    }

    static protected String getDeploymentNameFromPodName(String podName) {
        KubernetesClient client = new DefaultKubernetesClient();
        List<Deployment> des = client.extensions().deployments().list().getItems();

        for (Deployment deployment : des) {
            String deploymentName = deployment.getMetadata().getName();

            if (podName.startsWith(deploymentName)) {
                return deploymentName;
            }
        }
        return null;
    }

    private void labelNodeByKubectl(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
    }

    @RequestMapping(value = "/nodes/label", method = RequestMethod.GET)
    List getNodeLebels() {
        KubernetesClient client = new DefaultKubernetesClient();
        List<Node> nodeList = client.nodes().list().getItems();
        if (nodeList == null || nodeList.size()==0) {
            return null;
        }

        List labelList = new LinkedList();
        for (Node node : nodeList) {
            if ("k8s-ms".equals(node.getMetadata().getLabels().get("nodeLabel"))) {
                continue;
            }
            HashMap label = new HashMap();
            label.put("key", "nodeLabel");
            label.put("value", node.getMetadata().getLabels().get("nodeLabel"));
            labelList.add(label);
        }

        return labelList;
    }



}
