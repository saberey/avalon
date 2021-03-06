package com.avalon.ms.multithread.puzzle;

import java.util.concurrent.CountDownLatch;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月17日 下午5:26:26
 *@version
 */
public class ValueLatch<T> {

	private T value = null;
	private final CountDownLatch done = new CountDownLatch(1);
	
	public boolean isSet(){
		return (done.getCount() == 0);
	}
	
	public synchronized void setValue(T newValue){
		if(!isSet()){
			value = newValue;
			done.countDown();
		}
	}
	
	public T getValue() throws InterruptedException{
		done.await();
		synchronized (this) {
			return value;
		}
	}
}
