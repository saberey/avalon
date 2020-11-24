package com.avalon.ms.multithread.schedule;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *@description通过创建中间task控制执行任务时限
 *@author saber
 *@date 2018年2月28日 上午9:41:28
 *@version
 */
public class ExpiredScheduleTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ExpiredScheduleTest.class);
	
    public static final long EXPIREDTIME = 1*60*1000; //1分钟
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int taskSize = scanner.nextInt();
		System.out.println("start thread pool!");
		CountDownLatch cDownLatch = new CountDownLatch(taskSize);
		ScheduledExecutorService schExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
		for(int i=0;i<taskSize;i++){
			schExecutorService.execute(new ExpiredTask(cDownLatch,i));
		}
		
		try {
			cDownLatch.await();
			schExecutorService.shutdown();
			System.out.println("shutdown thread pool!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
class ExpiredTask implements Runnable{
	
	private CountDownLatch countDownLatch;
	private int value;
	
	public ExpiredTask(CountDownLatch countDownLatch,int value){
		this.countDownLatch = countDownLatch;
		this.value = value;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		System.out.println(Thread.currentThread().getName()+" ExpiredTask start!");
		InnerTask it1 = new InnerTask();
		Thread t1 = new Thread(it1,"it"+value);
		t1.start();
		try{
			while(!it1.isFinished()){
					if(System.currentTimeMillis() - startTime < ExpiredScheduleTest.EXPIREDTIME){
						
					}else{
						t1.interrupt();
						System.out.println("中断线程"+t1.getName());
						break;
					}
					TimeUnit.SECONDS.sleep(1);
			}
		}catch(InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println(Thread.currentThread().getName()+" ExpiredTask end!");
			countDownLatch.countDown();
		}
		
	}
	
	
	class InnerTask implements Runnable{
		
	    private boolean  finished;
	    
	    public boolean isFinished(){
	    	return finished;
	    }
	    
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				System.out.println(Thread.currentThread().getName()+" InnerTask start!");
				TimeUnit.SECONDS.sleep(301);
				finished = true;
				System.out.println(Thread.currentThread().getName()+" execute success!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(Thread.currentThread().getName()+" interrputed!");
				finished = false;
			} finally{
				System.out.println(Thread.currentThread().getName()+" InnerTask end!");
			}
				
		}
		
	}
}
