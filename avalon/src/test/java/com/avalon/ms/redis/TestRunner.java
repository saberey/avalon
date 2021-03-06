package com.avalon.ms.redis;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月4日 上午10:30:54
 *@version
 */
public class TestRunner {

	public static void main(String[] args) {
		
		Result result = JUnitCore.runClasses(AllTests.class);
		for(Failure failure: result.getFailures()){
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}
