package com.avalon.ms.timer.task;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avalon.ms.service.IPSwapService;

public class RegTask extends TimerTask {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ApplicationContext context;
	private IPSwapService ipsService;
	
	public RegTask() {
		// TODO Auto-generated constructor stub
		context = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
		ipsService = context.getBean(IPSwapService.class);
		logger.info("current thread {} regTask ojbect hashcode {}",Thread.currentThread().getName(),this.hashCode());
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info(" reg execute time {} ",System.nanoTime());
		try {
			ipsService.reg();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
