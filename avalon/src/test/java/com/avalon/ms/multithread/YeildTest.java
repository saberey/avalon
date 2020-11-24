package com.avalon.ms.multithread;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月7日 下午3:58:40
 *@version
 */
public class YeildTest {

	public static void main(String[] args) {
		
		
		Object obj = new Object();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (obj) {
					System.out.println(Thread.currentThread().getName()+" in "+LocalDateTime.now());
					try {
						TimeUnit.SECONDS.sleep(60);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()+" out"+LocalDateTime.now());
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (obj) {
					System.out.println(Thread.currentThread().getName()+" deal"+LocalDateTime.now());
				}
			}
		},"t2");
		
		t1.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.yield();// release the cpu  not the lock
		t2.start();
	}
}
