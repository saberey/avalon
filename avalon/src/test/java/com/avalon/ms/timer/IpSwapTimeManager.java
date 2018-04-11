package com.avalon.ms.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.avalon.ms.timer.task.QueryTask;
import com.avalon.ms.timer.task.RegTask;
import com.avalon.ms.timer.task.TransferTask;

public class IpSwapTimeManager {
	
	private static long reg_period_time = 5*1000;
	private static long transfer_period_time = 5*1000;
	private static long query_period_time = 5*1000;
	
	
	public static void main(String[] args) {
		
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
		
		RegTask regTask = new RegTask();
		ses.scheduleWithFixedDelay(regTask, 100, reg_period_time, TimeUnit.MILLISECONDS);
		
		QueryTask queryTask = new QueryTask();
		ses.scheduleWithFixedDelay(queryTask,500, query_period_time, TimeUnit.MILLISECONDS);
		
		TransferTask transferTask = new TransferTask();
		ses.scheduleWithFixedDelay(transferTask,1000, transfer_period_time, TimeUnit.MILLISECONDS);
	}
}
