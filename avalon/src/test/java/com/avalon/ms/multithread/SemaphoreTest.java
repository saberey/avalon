package com.avalon.ms.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月16日 下午5:12:42
 *@version
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(2);
		
		for (int i = 0; i < 2; i++) {
			pool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName()+"get chance");
						TimeUnit.SECONDS.sleep(10);
						System.out.println(Thread.currentThread().getName()+"release chance");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally{
						semaphore.release();
					}
				}
			});
		}
		
		
		pool.shutdown();
	}
}
