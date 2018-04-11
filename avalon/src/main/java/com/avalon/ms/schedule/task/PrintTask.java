package com.avalon.ms.schedule.task;

import java.util.TimerTask;

import com.avalon.ms.schedule.task.service.PrintService;

public class PrintTask extends TimerTask{

	private PrintService printService;
	
	public void init(){
		printService = new PrintService();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		printService(5);
	}
	
	
	public void printService(int size){
		printService.batchPrint(size);
	}
}
