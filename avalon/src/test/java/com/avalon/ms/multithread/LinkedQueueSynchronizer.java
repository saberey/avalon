package com.avalon.ms.multithread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@description:TODO
 *@author saber
 *@date 2017年12月28日 上午11:10:29
 *@version
 */
public class LinkedQueueSynchronizer extends AbstractQueuedSynchronizer {

	
	private final Lock lock = new ReentrantLock();
}
