package com.avalon.ms.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.avalon.ms.schedule.task.PrintTask;

public class ScheduleTimer {
     
	public static void main(String[] args) {
		ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
		
		PrintTask printTask = new PrintTask();
		schedulePool.scheduleWithFixedDelay(printTask, 0, 1*60*1000, TimeUnit.MILLISECONDS);
	}
}
