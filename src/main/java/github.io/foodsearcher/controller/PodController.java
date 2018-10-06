package top.yaoyongdou.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;
import top.yaoyongdou.controller.util.NodeMap;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.extensions.Deployment;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import top.yaoyongdou.model.NodeStatus;
import top.yaoyongdou.model.PodInstance;
import top.yaoyongdou.model.StatusMsg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static top.yaoyongdou.controller.ScheduleController.getDeploymentNameFromPodName;

/**
 * Created by young on 18-3-6.
 */

@RestController
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*")
public class PodController {

    @RequestMapping(value = "/pods", method = RequestMethod.GET)
    public List getPods(@RequestParam(value = "namespace", defaultValue = "default") String namespace,
                        @RequestParam(value = "type", defaultValue = "all") String type) {
        KubernetesClient client = new DefaultKubernetesClient();

        List<PodInstance> podInstanceList = new LinkedList<>();
        Map<String, NodeStatus> nodeMap = NodeMap.getNodeStatusMap();

        List<Pod> mmePodsList = null;
        List<Pod> hssPodsList = null;

        // TODO: 18-3-6 spgw
//        List<PodInstance.PodInfo> spgwList = new LinkedList<>();
        if (type.equals("mme") || type.equals("all")) {
            mmePodsList = client.pods().inNamespace(namespace)
                    .withLabel("eleType", "mme").list().getItems();
            List<PodInstance.PodInfo> mmeList = getNetEleList(mmePodsList, nodeMap);
            podInstanceList.add(new PodInstance("mme", mmeList));
        }

        if (type.equals("hss") || type.equals("all")) {
            hssPodsList = client.pods().inNamespace(namespace)
                    .withLabel("eleType", "hss").list().getItems();
            List<PodInstance.PodInfo> hssList = getNetEleList(hssPodsList, nodeMap);
            podInstanceList.add(new PodInstance("hss", hssList));
        }

        return podInstanceList;
    }


    public List<PodInstance.PodInfo> getNetEleList(List<Pod> podsList, Map<String, NodeStatus> nodeMap) {
        List<PodInstance.PodInfo> netEleList = new LinkedList<>();

        for (Pod pod : podsList) {
            String podName = pod.getMetadata().getName();
            String nodeName = pod.getSpec().getNodeName();

            if (nodeMap == null) {
                return null;
            }

            //调度失败or等待调度的pod
            if (nodeName == null) {
                nodeName = NodeMap.noneNode;
            }
            NodeStatus nodeStatus = nodeMap.get(nodeName);
            PodInstance.PodInfo podInfo =
                    new PodInstance.PodInfo(podName, nodeName, pod.getStatus().getPhase(), nodeStatus.getCpu(), nodeStatus.getMem(), nodeStatus.getNet());

            netEleList.add(podInfo);
        }

        return netEleList;

    }

    @RequestMapping(value = "/pods", method = RequestMethod.POST)
//    @RequiresAuthentication
    public StatusMsg addScaleInstance(@RequestParam("type") String type) {
        KubernetesClient client = new DefaultKubernetesClient();

        File yamlTemplateFile = null;

        System.out.println("pod type " + type);

        if (type.equals("mme")) {
            yamlTemplateFile = new File("src/main/resources/mme.yaml");
        }
        else if (type.equals("hss")) {
            yamlTemplateFile = new File("src/main/resources/hss.yaml");
        }
        else {
            return StatusMsg.returnError();
        }

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(yamlTemplateFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return StatusMsg.returnError();
        }
        List<HasMetadata> refresh = client.load(inputStream).inNamespace("default").get();
        if (refresh.isEmpty()) {
            return StatusMsg.returnError();
        }

        Deployment deploymentTemp = (Deployment) refresh.get(0);
        ObjectMeta meta = deploymentTemp.getMetadata();

        String deployName = meta.getName();
        String timeTag = getTimeTag();
        String newDeployName = deployName + timeTag;
        meta.setName(newDeployName);

        deploymentTemp.setMetadata(meta);

        client.extensions().deployments().create(deploymentTemp);

        return StatusMsg.returnOk();
    }

    private String getTimeTag() {
        Date nowTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("-yyyyMMddHHmmss");
        return format.format(nowTime);
    }

    @RequestMapping(value = "/pods/{podName}", method = RequestMethod.DELETE)
    public StatusMsg deletePod(@PathVariable("podName") String podName) {
        KubernetesClient client = new DefaultKubernetesClient();

        String deploymentName = getDeploymentNameFromPodName(podName);
        Deployment deployment
                = client.extensions().deployments().inNamespace("default").withName(deploymentName).get();
        if (deployment == null) {
            return StatusMsg.returnError();
        }
        if (client.extensions().deployments().delete(deployment)) {
            return StatusMsg.returnOk();
        }

        return StatusMsg.returnError();
    }

}
