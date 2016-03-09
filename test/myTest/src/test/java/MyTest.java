import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by adon on 2016/3/1 0001.
 */
public class MyTest {

	@Test
	public void testSystem1(){

//		System.out.println(System.getProperties());

		Enumeration enums = System.getProperties().keys();

		StringBuilder sb = new StringBuilder();
		while (enums.hasMoreElements()){
			String key = (String) enums.nextElement();
			sb.append(key)
					.append(" ------> ")
					.append(System.getProperty(key))
					.append(System.lineSeparator());
		}

		Iterator<Map.Entry<String, String>> it = System.getenv().entrySet().iterator();
		sb.append("-------------------").append(System.lineSeparator());
		while (it.hasNext()){
			Map.Entry<String, String> entry = it.next();
			sb.append(entry.getKey())
					.append(" ------> ")
					.append(entry.getValue())
					.append(System.lineSeparator());
		}

		System.out.println(sb.toString());
	}

	@Test
	public void testSystem2(){
		int[] arr = {1,3,4,5,6,8};
		int[] arr2 = new int[10];
		System.arraycopy(arr, 0, arr2, 3, arr.length);
		for ( int i : arr2){
			System.out.print(i+",");
		}
	}

	@Test
	public void testSystem3(){
		String aa = "2klsdj";

		System.out.println(aa.hashCode());
		System.out.println(System.identityHashCode(aa));
		System.out.println(System.nanoTime());
		System.out.println(System.currentTimeMillis());
		System.out.println(System.mapLibraryName("aa"));



	}

	@Test
	public <T> void classTest() throws ClassNotFoundException {
		Class<T> clz = (Class<T>) Class.forName(String.class.getName());
		Object o = "123";
		T t = (T) o;
		System.out.println(o.getClass());
	}

	@Test
	public void localeTest()  {

	}

//	@Test
//	public void InternalTest(){
//		MessageUtil.getMessage(MessageUtil.SERVICE_RESOURCE_NAME, "LoginController.Error_password", Locale.CHINA);
//		long start = System.currentTimeMillis();
//		for (int i = 0; i<1000000; i++){
//			MessageUtil.getMessage(MessageUtil.SERVICE_RESOURCE_NAME, "LoginController.Error_password", Locale.CHINA);
//		}
//		System.out.println("getMessage use time: "+ (System.currentTimeMillis()-start) + "ms.");
//		start = System.currentTimeMillis();
//		for (int i = 0; i<1000000; i++){
//			MessageUtil.getMessage1(MessageUtil.SERVICE_RESOURCE_NAME, "LoginController.Error_password", Locale.CHINA);
//		}
//		System.out.println("getMessage1 use time: "+ (System.currentTimeMillis()-start) + "ms.");
//	}


	public static volatile Integer count = 0;

	public static AtomicBoolean flag = new AtomicBoolean(true);
	public static boolean mainFlag = true;

	public static void doFun() throws InterruptedException {

		if(mainFlag && flag.compareAndSet(true, false)){
			System.out.println(Thread.currentThread() +"("+System.currentTimeMillis()+")" + ": [" + flag + "] "+ (++count));
			flag.set(true);
		} else {
			System.out.println(Thread.currentThread() +"("+System.currentTimeMillis()+")" + ": [" + flag + "] 不满足条件.");
			Thread.sleep(2);
		}

	}

	static boolean atomicFlag = true;
	static boolean syncFlag = true;
	static boolean reenFlag = true;
	static long start;

	@Test
	public void threadTest(){

		final int max = 10000;
		final int maxTread = 4;

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (atomicFlag){
					if(flag.compareAndSet(true, false)){
						count++;
//						System.out.println(Thread.currentThread() +"("+System.currentTimeMillis()+")" + ": [" + flag + "] "+ count);
						if(count<max){
							flag.set(true);
						} else {
							atomicFlag = false;
							System.out.println("("+Thread.currentThread()+")atomic used time:["+(System.currentTimeMillis()-start)+"], count:"+count);
							count = 0;
						}
					} else {
//						System.out.println(Thread.currentThread() +"("+System.currentTimeMillis()+")" + ": [" + flag + "] 不满足条件.");
						try { Thread.sleep(1); } catch (InterruptedException e) {}
					}
				}
			}


		};


		Runnable syncRunnable = new Runnable() {
			@Override
			public void run() {
				while (syncFlag){
					synchronized (MyTest.class){
						if(syncFlag){
							count++;
//							System.out.println(Thread.currentThread() +"("+System.currentTimeMillis()+")" + ": "+ count);
							if(count>=max){
								syncFlag = false;
								System.out.println("("+Thread.currentThread()+")synchronized used time:["+(System.currentTimeMillis()-start)+"], count:"+count);
								count = 0;
							}
						}
					}
				}
			}
		};

		final ReentrantLock lock = new ReentrantLock();

		Runnable reenRunnable = new Runnable() {
			@Override
			public void run() {
				while (reenFlag){
					try{
						lock.lock();
						if(reenFlag){
							count++;
							if(count>=max){
								reenFlag = false;
								System.out.println("("+Thread.currentThread()+")reentrantLock used time:["+(System.currentTimeMillis()-start)+"], count:"+count);
								count = 0;
							}
						}
					} finally {
						lock.unlock();
					}
				}
			}
		};



		try {
			start = System.currentTimeMillis();
			for(int	i = 0; i<maxTread; i++){
				new Thread(syncRunnable).start();
			}
			while (syncFlag){
				Thread.sleep(1000);
			}

			start = System.currentTimeMillis();
			for(int	i = 0; i<maxTread; i++){
				new Thread(runnable).start();
			}
			while (atomicFlag){
				Thread.sleep(1000);
			}

			start = System.currentTimeMillis();
			for(int	i = 0; i<maxTread; i++){
				new Thread(reenRunnable).start();
			}
			while (reenFlag){
				Thread.sleep(1000);
			}

//			mainFlag = false;
			System.out.println(Thread.currentThread() + ": [" + flag + "] "+count);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
