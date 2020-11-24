package com.avalon.ms.multithread.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月6日 下午5:21:10
 *@version
 */
public class SchedulePTest {

	public static void main(String[] args) {
		ScheduledExecutorService spools = Executors.newScheduledThreadPool(4);
		CountDownLatch cdl = new CountDownLatch(4);
		List<FutureTask> taskList = new ArrayList();
		for (int i = 0; i < 4; i++) {
			FutureTask<String> future = new FutureTask<String>(new SingleTask(cdl));
			taskList.add(future);
			spools.execute(future);
		}
		
		ScheduledExecutorService resultPool = Executors.newScheduledThreadPool(taskList.size());
		
		for( int i=0;i<taskList.size();i++){
			FutureTask<String> future = taskList.get(i);
			resultPool.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName()+" willing to get future result for "+future);
					try {
						String result = future.get(5, TimeUnit.SECONDS);
						System.out.println(Thread.currentThread().getName()+"got");
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						future.cancel(true);
						System.out.println(future+"canceled");
					}
				}
			});
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spools.shutdown();
		resultPool.shutdown();
	}
}
