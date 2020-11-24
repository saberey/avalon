package com.avalon.ms.multithread.synchronizer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月13日 下午5:41:27
 *@version
 */
public class SemaphoreTest {
	final Semaphore request  = new Semaphore(1);
	final Semaphore response  = new Semaphore(0);
	
	public  void getIn() throws InterruptedException{
		request.acquire();
		System.out.println(" request in ");
		response.release();
		System.out.println(" request out ");
	}
	
	public void getOut() throws InterruptedException{
		response.acquire();;
		System.out.println(" response in");
		request.release();
		System.out.println(" response out");
	}
	
	public static void main(String[] args) {
		SemaphoreTest semaphoreTest = new SemaphoreTest();
		Thread request = new Thread(new Task2(semaphoreTest),"req");
		Thread response = new Thread(new Task1(semaphoreTest),"res");
		
		response.start();
		request.start();
		
	}
}
class Task2 implements Runnable{
	private SemaphoreTest semaphoreTest;
	public Task2(SemaphoreTest semaphoreTest){
		this.semaphoreTest = semaphoreTest;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(Thread.currentThread().getName()+" in");
			semaphoreTest.getOut();
			System.out.println(Thread.currentThread().getName()+" get resources. ");
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			System.out.println(Thread.currentThread().getName()+" end ");
		}
	}
}
class Task1 implements Runnable{
	
	private SemaphoreTest semaphoreTest;
    public Task1(SemaphoreTest semaphoreTest) {
		// TODO Auto-generated constructor stub
		this.semaphoreTest = semaphoreTest;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(Thread.currentThread().getName()+" in");
			semaphoreTest.getIn();
			System.out.println(Thread.currentThread().getName()+" get resources. ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			System.out.println(Thread.currentThread().getName()+" end ");
		}
	}
}