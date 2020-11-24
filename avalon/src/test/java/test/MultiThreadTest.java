package test;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过threadlocal记录线程执行时的状态，在线程调用的业务逻辑时通过保存的状态和当前的状态进行比较
 * 如果状态已经变更过(其他线程引起的变更)，则不参与此次变更逻辑的判断，直接返回
 * eg:此次测试程序业务变更逻辑如下，业务类初始化变更次数为times，变更标志boolean,
 * 同步变更方法(每次调用变更次数减1，小于0后更改boolean标志)
 * 如果不用threadlocal保存状态，会出现多个线程在等待同步变更方法时boolean可能已经被变更过了，
 * 如果继续放任这几个线程继续执行同步变更方法，会到只boolean继续被变更，从而导致boolean会被不正确的变更，
 * 所以通过threadlocal保存状态，判断如果本线程的保存状态与当前状态不一致则不执行同步变更方法。
 *@descriptionTODO
 *@author saber
 *@date 2017年9月19日 下午3:27:03
 *@version
 */
public class MultiThreadTest {

	private static int times = 2;
	private boolean flag = false;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ThreadLocal<Boolean> tl = new ThreadLocal<Boolean>();
	
	public void service(){
		try{
			logger.info("sleep time {} flag {}",System.nanoTime(),flag);
			tl.set(flag);
			TimeUnit.SECONDS.sleep(5);
			change();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public synchronized void change(){
		if(tl.get()!=flag){
			logger.info("tl flag {} return",tl.get());
			return;
		}else{
			logger.info("times {} flag {} time {}",times,flag,System.nanoTime());
			if(!flag){
				times--;
				if(times<0){
					times=2;
					flag = true;
					logger.info("change flag {} ",flag);
				}
			}else{
				times--;
				if(times<0){
					times=2;
					flag = false;
					logger.info("change flag {} ",flag);
				}
			}
			logger.info("times {} flag {} time {}",times,flag,System.nanoTime());
		}
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		MultiThreadTest multiThreadTest = new MultiThreadTest();
		Method method = multiThreadTest.getClass().getMethod("service", null);
		ScheduledExecutorService pools = Executors.newScheduledThreadPool(4);
		Callable<Object> callable = new Callable<Object>() {
			
			@Override
			public Object call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		};
		pools.submit(callable);
	    CountDownLatch countDownLatch = new CountDownLatch(4);
		for(int i=0;i<20;i++){
			try {
				pools.execute(new ServiceTask(multiThreadTest, countDownLatch,method));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				countDownLatch.countDown();
			}
		}
		
		try {
			countDownLatch.await();
			pools.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class ServiceTask implements Runnable{
	
	private MultiThreadTest multiThreadTest;
	private CountDownLatch coutDownLatch;
	private Method method;
	
	public ServiceTask(MultiThreadTest multiThreadTest,CountDownLatch coutDownLatch,Method method){
		this.multiThreadTest = multiThreadTest;
		this.coutDownLatch = coutDownLatch;
		this.method = method;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			method.invoke(multiThreadTest, null);
		}catch(Exception e){
			
		}finally{
			coutDownLatch.countDown();
		}
	}
}
