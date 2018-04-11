package com.avalon.ms.schedule.job;

import java.util.Random;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticJob implements SimpleJob{

	@Override
	public void execute(ShardingContext arg0) {
		// TODO Auto-generated method stub
		test();
	}
	
	
	public void test(){
		int no = new Random().nextInt();
		System.out.println(System.currentTimeMillis()+" "+no);
	}
}
