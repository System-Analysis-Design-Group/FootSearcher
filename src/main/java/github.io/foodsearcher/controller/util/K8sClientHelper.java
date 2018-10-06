package top.yaoyongdou.controller.util;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * Created by young on 18-3-6.
 */
public class K8sClientHelper {

    static public KubernetesClient getK8sClient() {
        KubernetesClient client = new DefaultKubernetesClient();
        return client;
    }
}
