import com.demo.common.util.ServiceLocator;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeoutException;

/**
 * Created by adon on 2016/2/22 0022.
 */
public class MemcacheTest {

	static {
		new ClassPathXmlApplicationContext("/spring-cache.xml");
	}

	@Test
	public void test() throws InterruptedException, MemcachedException, TimeoutException {
		XMemcachedClient client = (XMemcachedClient) ServiceLocator.getBean("memCachedClient");

		client.set("testKey", 10, "123");

		System.out.println(client.get("testKey"));

	}

	@Test
	public void test2() throws InterruptedException, MemcachedException, TimeoutException {
		XMemcachedClient client = (XMemcachedClient) ServiceLocator.getBean("memCachedClient");

		System.out.println(client.get("testKey"));

	}

}
