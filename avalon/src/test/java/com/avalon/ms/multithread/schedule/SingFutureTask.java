package com.avalon.ms.multithread.schedule;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *@description:TODO
 *@author saber
 *@date 2018年2月6日 下午5:47:12
 *@version
 */
class SingFutureTask{
		
	private static final String END = "end";
	
	private static volatile int v = 0;
	
	public static void main(String[] args){
		
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		CompletionService<String> ecs = new ExecutorCompletionService<String>(executor);
		CountDownLatch countDownLatch = new CountDownLatch(21);
		Map<String,Integer> indexCount = new ConcurrentHashMap<>(2);
		for (int i = 0; i < 21; i++) {
			indexCount.put("i", i);
			ecs.submit(new Callable<String>() {
				@SuppressWarnings("finally")
				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					try{
						System.out.println(Thread.currentThread().getName());
						int key = indexCount.get("i");
						System.out.println("key:"+key);
						if(indexCount.get("i")%20==0){
							System.out.println("!!!!!!!!in 20");
							TimeUnit.SECONDS.sleep(50);
						}
					}catch(InterruptedException ie){
						System.out.println(Thread.currentThread().getName()+"interrupted");
						throw new InterruptedException("time out");
					}finally{
						countDownLatch.countDown();
						return END;
					}
				}
			});
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<21;i++){
			try {
				System.out.println("status|get|"+i);
				Future<String> future = ecs.take();
				System.out.println("future get ");
				String result = null;
				try {
					result = future.get(20,TimeUnit.SECONDS);
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					future.cancel(true);
				}
				System.out.println(future.toString()+" result: "+result);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
}

