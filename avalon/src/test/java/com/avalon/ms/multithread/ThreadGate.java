package com.avalon.ms.multithread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 *@description:TODO
 *@author saber
 *@date 2017年12月22日 上午10:55:24
 *@version
 */
@ThreadSafe
public class ThreadGate {
	
	//条件谓词: opened-since(n) (isOpen || generation > n)
	@GuardedBy("this") private boolean isOpen;
	@GuardedBy("this") private int generation;
	 
	public synchronized void close(){
		isOpen = false;
	}
	
	public synchronized void open(){
		++generation;
		isOpen = true;
		notifyAll();
	}
	//阻塞并直到: opened-since(generation on entry)
	public synchronized void await() throws InterruptedException {
		int arrivalGeneration = generation;
		while(!isOpen && arrivalGeneration == generation)
			wait();
	}
}
