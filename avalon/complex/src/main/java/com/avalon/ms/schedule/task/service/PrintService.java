package com.avalon.ms.schedule.task.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avalon.ms.common.util.HttpClientUtil;


public class PrintService {

	private static  final  String uri = "http://localhost:8080/ms/city/code";
	
	private Logger logger = LoggerFactory.getLogger(PrintService.class);
	
	public void batchPrint(int size){
		logger.info("print task start! size {}",size);
		ExecutorService printPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
		CountDownLatch countDownLatch = new CountDownLatch(size);
		for(int i =0;i<size;i++){
			printPool.execute(new PrintServiceThread(this, countDownLatch,i));
			
		}
		try{
			countDownLatch.await();
			printPool.shutdown();
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("print task end!");
	}
	
	public void print(final int index){
		Map<String,String> nameValueMap = new HashMap(){{put("city",String.valueOf(index));}};
		Object[] result = HttpClientUtil.httpPost(uri, nameValueMap);
		logger.info("http response {}",result[0]);
		logger.info("http response content {}",new String((byte[])result[1]));
	}
	
	public class PrintServiceThread implements Runnable {
		
		private PrintService printService;
		private CountDownLatch countDownLatch;
		private int index;
		
		public PrintServiceThread(PrintService printService,CountDownLatch countDownLatch,int index){
			this.printService = printService;
			this.countDownLatch = countDownLatch;
			this.index = index;
		}
		
		public void run() {
			try{
				printService.print(index);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				countDownLatch.countDown();
			}
		}
	}
	
	public static void main(String[] args) {
		PrintService printService = new PrintService();
		printService.print(1);
	}
}
