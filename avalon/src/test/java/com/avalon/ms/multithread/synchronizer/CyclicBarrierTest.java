package com.avalon.ms.multithread.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *@description:TODO
 *@author saber
 *@date 2017年12月21日 上午11:34:00
 *@version
 */
public class CyclicBarrierTest {

	private   int count;
	private  CyclicBarrier cBarrier;

	public CyclicBarrierTest(int count){
		this.count = count;
		cBarrier = new CyclicBarrier(count, new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("enough,It's time to go!");
			}
		});
	}
	
	public void getOut(String in) throws InterruptedException, BrokenBarrierException{
		System.out.println(in+" arrive.");
		cBarrier.await();
		System.out.println(in+" go.");
	}
	
	public static void main(String[] args) {
		
		final CyclicBarrierTest cBarrierTest = new CyclicBarrierTest(10);
		
		for (int i = 0; i < 20; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						cBarrierTest.getOut(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}).start();
		}
	}
}
