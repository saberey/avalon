package com.avalon.ms.concurrent;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 *@description:TODO
 *@author saber
 *@date 2018年2月8日 下午4:28:48
 *@version
 */
public class ArTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		AtomicReferenceTest art = new AtomicReferenceTest();
		final CountDownLatch gateway = new CountDownLatch(1);
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						gateway.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int i = random.nextInt();
					System.out.println(Thread.currentThread().getName()+" set lower start |"+i);
					art.setLower(i);
					System.out.println(Thread.currentThread().getName()+" set lower end");
				}
			},"task-"+i);
			t.start();
		}
		gateway.countDown();
		
		System.out.println("！end!");
		
	}
}
