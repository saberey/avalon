package com.avalon.ms.java8;

import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月15日 上午11:17:48
 *@version
 */
public class StreamTest2 {
	
	private static Logger logger = LoggerFactory.getLogger(StreamTest2.class);
	
	
	
	static List<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 5000000; i++) {
			list.add(i);
		}
		int result = 0;
		LocalDateTime start = LocalDateTime.now();
		for (int i : list) {
			if(i%2==0)
				result +=i;
		}
		LocalDateTime end = LocalDateTime.now();
		Duration duration = Duration.between(start, end);
		System.out.println(result);
		System.out.println("cost:"+duration.toMillis());
		
		PrintWriter pw = new PrintWriter(System.out);
		pw.write("hello world");
		logger.info("test");
		ExecutorService pool = Executors.newCachedThreadPool();
		pool.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("before");
				try {
					TimeUnit.SECONDS.sleep(10);
					System.out.println("wake");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		List<Runnable> canceledTask = pool.shutdownNow();
		if(pool.awaitTermination(8, TimeUnit.SECONDS)){
			System.out.println("await");
		}
		if(!canceledTask.isEmpty()){
			for(Runnable r : canceledTask){
				System.out.println(r.toString());
			}
		}
	}
}
