
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

public class ZssTest {

    public static void main(String[] args) {
        /*
        ApplicationConfig application = new ApplicationConfig();
        application.setName("ap-dubbo-client");

        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
        registry.setAddress("192.168.1.107");
        registry.setPort(2181);

        ReferenceConfig<DataCollectIF> reference = new ReferenceConfig<DataCollectIF>();
        reference.setApplication(application);
        reference.setInterface("com.blossom.service.api.collect.DataCollectIF");

        reference.setRegistry(registry);

        DataCollectIF dataCollectIF = null;
        for (int i = 0; i < 1000; i++) {
            try {
                dataCollectIF = reference.get();
                dataCollectIF.count("/zss" + i);
                System.out.println("count call "+i);
            } catch (Exception e) {
                int k = 0;
                // ignore error
            }
        }

*/
        int k = 0;
    }
}
