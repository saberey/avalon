package com.avalon.ms.timer.task;

import java.lang.reflect.Method;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avalon.ms.service.IPSwapService;

public class QueryTask extends TimerTask {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private ApplicationContext context;
	private IPSwapService ipsService;
	
	public QueryTask() {
		// TODO Auto-generated constructor stub
		context = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
		ipsService = context.getBean(IPSwapService.class);
		logger.info("current thread {} queryTask ojbect hashcode {}",Thread.currentThread().getName(),this.hashCode());
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("thread {} query execute  ",Thread.currentThread().getName());
		try{
			int limit = Runtime.getRuntime().availableProcessors();
			ExecutorService pools = Executors.newScheduledThreadPool(limit);
			final CountDownLatch countDownLatch = new CountDownLatch(6);
			final Method method = ipsService.getClass().getDeclaredMethod("query", null);
			logger.info("开启多线程执行查询任务，线程数"+6);
			for(int i=0;i<6;i++){
				try{
					pools.execute(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try{
								method.invoke(ipsService, null);
							}catch(Exception e){
								e.printStackTrace();
							}finally{
								countDownLatch.countDown();
							}
						}
					});
				}catch(Exception e){
					e.printStackTrace();
					countDownLatch.countDown();
				}
			}
			try{
				countDownLatch.await();
				pools.shutdown();
				logger.info("关闭多线程");
			}catch(Exception e){
				e.printStackTrace();
				logger.info("多线程关闭出错");
			}
			
		}catch(Exception e){
			logger.info("thread {} query error {}",Thread.currentThread().getName(),e.getMessage());
		}
	}

}
