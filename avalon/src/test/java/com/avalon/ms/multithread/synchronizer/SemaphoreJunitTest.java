package com.avalon.ms.multithread.synchronizer;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月14日 上午11:17:47
 *@version
 */
public class SemaphoreJunitTest {

	private BoundedBuffer<Integer> boundedBuffer;
	
	@Before
	public void init(){
		boundedBuffer = new BoundedBuffer<Integer>(10);
	}
	//@Test
	public void test() {
		fail("Not yet implemented");
	}
	//@Test
	public void testIsEmptyConstructed(){
		//BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<Integer>(5);
		assertTrue(boundedBuffer.isEmpty());
		assertFalse(boundedBuffer.isFull());
	}
	
	//@Test
	public void testIsFullAfterPuts() throws InterruptedException{
		for (int i = 0; i < 10; i++) {
			boundedBuffer.put(i);
		}
		assertTrue(boundedBuffer.isFull());
		assertFalse(boundedBuffer.isEmpty());
	}
	
	@Test
	public void testTakeBlockWhenEmpty(){
		Thread taker = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					boundedBuffer.take();
					fail();
				} catch (InterruptedException e) {
					// TODO: handle exception
					System.out.println("interrupted");
				}
			}
		});
		
		try {
			taker.start();
			TimeUnit.SECONDS.sleep(10);
			taker.interrupt();
			taker.join(10000);
			assertFalse(taker.isAlive());
		} catch (Exception e) {
			// TODO: handle exception
			fail();
		}
	}
	
	class Big{
		double[] data = new double[100000];
	}
	
	//@Test
	public void testLeak(){
		
	}
}
