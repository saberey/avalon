package com.avalon.ms.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月20日 上午9:42:57
 *@version
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
	}
}
