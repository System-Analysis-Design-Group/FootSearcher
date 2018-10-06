import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.extensions.Deployment;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by young on 18-3-5.
 */
public class testK8s {
    public static void main(String[] args) throws FileNotFoundException {
//        List<NodeStatus> nodeStatusList = new LinkedList<>();
//
//        KubernetesClient client = new DefaultKubernetesClient();

        Date date = new Date();
        System.out.println(date.toString());

//        List<Deployment> des = client.extensions().deployments().list().getItems();
//
//        client.componentstatuses().list();
//
//        FilterWatchListDeletable<Pod, PodList, Boolean, Watch, Watcher<Pod>> pods = client.pods().withLabelIn("k8s-ms");
//        return;

//        KubernetesClient client = new DefaultKubernetesClient();
//
//        File yamlFile = new File("src/main/resouce/mme.yaml");
//        InputStream inputStream = new FileInputStream(yamlFile);
//        List<HasMetadata> refresh = client.load(inputStream).inNamespace("default").get();
//
//
//        Deployment de = (Deployment) refresh.get(0);
//        client.extensions().deployments().create(de);

//        Service svc = client.services().inNamespace("default").withName("mme-nginx-svc").get();
//        String algorithmName = "md5";
//        String username = "admin";
//        String password = "123456";
//        String salt1 = "adminsalt";
//        int hashIterations = 2;
//
//        SimpleHash hash = new SimpleHash(algorithmName, password, salt1, hashIterations);
//        String encodedPassword = hash.toHex();
//        System.out.println(encodedPassword);




        return;


    }
}
