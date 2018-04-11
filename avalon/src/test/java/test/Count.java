package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月16日 上午11:14:34
 *@version
 */
public class Count {

	public int count = 0;
	
	public AtomicInteger atomicInteger = new AtomicInteger(0);
	
	
	public synchronized  void add(){
		count ++;
	}
	
	
	
	static class Job implements Runnable{
		
		private CountDownLatch  countDownLatch;
		private Count count;
		public Job(CountDownLatch  countDownLatch,Count count){
			this.countDownLatch = countDownLatch;
			this.count = count;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			count.count++;
			//count.add();
			//count.atomicInteger.addAndGet(1);
			boolean isSuccess = false;
			while(!isSuccess){
				int countValue = count.atomicInteger.get();
				isSuccess = count.atomicInteger.compareAndSet(countValue, countValue+1);
			}
			countDownLatch.countDown();
		}
	}
	
	public static void main(String[] args) {
		CountDownLatch countDown = new CountDownLatch(1500);
		Count count = new Count();
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (int i = 0; i < 1500; i++) {
			pool.execute(new Job(countDown,count));
		}
		
		try {
			countDown.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count.count);
		System.out.println(count.atomicInteger.get());
		pool.shutdown();
	}
}
