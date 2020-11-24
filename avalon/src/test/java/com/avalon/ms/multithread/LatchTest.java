package com.avalon.ms.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月13日 下午5:22:11
 *@version
 */
public class LatchTest {

	public static void main(String[] args) {
		CountDownLatch inner = new CountDownLatch(1);
		CountDownLatch outer = new CountDownLatch(2);
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try{
						System.out.println(Thread.currentThread().getName()+" waiting ");
						inner.await();
						System.out.println(Thread.currentThread().getName()+" start work ");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						outer.countDown();
					}
				}
			}).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(10);
			System.out.println("main sleep 10 s");
			inner.countDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread t1 = new Thread("test1");
		Thread t2 = new Thread("test2");
		t1.start();
		t2.start();
		try {
			t2.join(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
