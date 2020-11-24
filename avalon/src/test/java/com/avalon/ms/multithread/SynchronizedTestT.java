package com.avalon.ms.multithread;

import java.util.concurrent.Exchanger;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年1月10日 下午2:52:57
 *@version
 */
public class SynchronizedTestT {

	public static void main(String[] args) {
		
		
		SynchronizedTestC synchronizedTestC = new SynchronizedTestC();
		SynchronizedTestC synchronizedTestC2 = new SynchronizedTestC();
		
		//synchronizedTestC2.test2();
		//synchronizedTestC.test1();
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronizedTestC.test3();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronizedTestC2.test3();
			}
		},"t2");
		
		t.start();
		t2.start();
	}
}
