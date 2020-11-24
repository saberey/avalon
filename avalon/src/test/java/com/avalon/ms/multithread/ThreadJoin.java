package com.avalon.ms.multithread;

import java.util.concurrent.TimeUnit;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月8日 下午12:15:56
 *@version
 */
public class ThreadJoin {

	public static void main(String[] args) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				System.out.println("start!");
				while(true){
					System.out.println(i++);
					try {
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		thread.start();
		try {
			thread.join(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TimeUnit
		System.out.println("end");
	}
}
