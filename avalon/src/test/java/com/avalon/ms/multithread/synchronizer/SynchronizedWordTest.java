package com.avalon.ms.multithread.synchronizer;

import java.util.concurrent.TimeUnit;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月8日 上午9:41:58
 *@version
 */
public class SynchronizedWordTest {

	
	private int id;
	private static String content;
	
	public SynchronizedWordTest(int id,String content){
		this.id = id;
		this.content = content;
	}
	
	public synchronized  void test(){
		
		try {
			System.out.println("in test");
			TimeUnit.SECONDS.sleep(10);
			content = "test";
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			System.out.println("test1 content:"+content);
			System.out.println("out test");
			printTrace();
		}
	}
	
	public synchronized static  void test2(){
		try {
			System.out.println("in test2");
			TimeUnit.SECONDS.sleep(5);
			content = "test2";
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			System.out.println("test2 content:"+content);
			System.out.println("out test2");
		}
	} 
	
	public void printTrace(){
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		if(st == null){
			return;
		}
		StringBuffer sbf = new StringBuffer();
		for(StackTraceElement e:st){
			if(sbf.length()>0){
				sbf.append(" <- ");
				sbf.append(System.getProperty("line.separator"));
			}
			sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}",e.getClassName(),e.getMethodName(),e.getLineNumber()));
		}
		System.out.println(sbf.toString());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return "SynchronizedWordTest[id:"+id+",content:"+content+"]";
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		SynchronizedWordTest swt = new SynchronizedWordTest(1, "1c");
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				swt.test();
			}
		},"TestThread");
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				SynchronizedWordTest.test2();
			}
		},"TestThread");
		
		t1.start();
		TimeUnit.SECONDS.sleep(2);
		t2.start();
		
		try{
			t1.join();
			t2.join();
		}finally{
			System.out.println(swt.toString());
		}
		
	}
}
