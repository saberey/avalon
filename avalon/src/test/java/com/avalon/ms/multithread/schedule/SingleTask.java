package com.avalon.ms.multithread.schedule;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 *@description:TODO
 *@author saber
 *@date 2018年2月6日 下午5:16:34
 *@version
 */
public class SingleTask implements Callable<String>{
	
	private static final String END = "end";
	private CountDownLatch  cdl;
	
	public  SingleTask(CountDownLatch cdl) {
		// TODO Auto-generated constructor stub
		this.cdl = cdl;
	}
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		try{
			TimeUnit.SECONDS.sleep(10);
			System.out.println("11");
			System.out.println(Thread.currentThread().getName());
			
		}catch(IllegalArgumentException e){
			e.printStackTrace();
			System.out.println("singleTask throw Exception!");
		}finally{
			System.out.println(Thread.currentThread().getName()+" finally");
			cdl.countDown();
			return END;
		}
	}

}
