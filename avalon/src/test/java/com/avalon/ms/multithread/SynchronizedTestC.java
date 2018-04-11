package com.avalon.ms.multithread;

import java.util.concurrent.TimeUnit;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月10日 下午2:51:43
 *@version
 */
public class SynchronizedTestC {

	private  Integer i = 130;
	
	public synchronized void test1(){
		
		System.out.println("1");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1");
	}
	
	
	
	public synchronized void test2(){
		
		System.out.println("2");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2");
	}
	
	
	public void test3(){
		synchronized (this) {
			System.out.println(this.getClass());
			System.out.println(this.hashCode());
			System.out.println("3");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("3");
		}
	}
}
