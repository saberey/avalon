package com.avalon.ms.multithread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月16日 下午5:33:43
 *@version
 */
public class LoggingThreadPool extends ThreadPoolExecutor {

	public LoggingThreadPool(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// TODO Auto-generated constructor stub
	}
	
	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private final Logger log = Logger.getLogger("LoggingThreadPool");
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		// TODO Auto-generated method stub
		super.beforeExecute(t, r);
		log.fine(String.format("Thread %s: start %s", t,r));
		startTime.set(System.nanoTime());
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		// TODO Auto-generated method stub
		try {
			long endTime = System.nanoTime();
			long taskTime = endTime-startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			log.fine(String.format("Thread %s: end %s, time=%dns", t,r,taskTime));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			super.afterExecute(r, t);
		}
		
	}
	
	@Override
	protected void terminated() {
		// TODO Auto-generated method stub
		try {
			log.info(String.format("Terminated: avg time=%dns", totalTime.get()/numTasks.get()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			super.terminated();
		}
	}
}
