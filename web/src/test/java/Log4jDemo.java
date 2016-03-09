import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by adon on 2016/1/30 0030.
 */
public class Log4jDemo {

	protected Logger log = Logger.getLogger(this.getClass());

	@Test
	public void test(){
		log.warn("123456");
	}


}
