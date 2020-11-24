package com.avalon.ms.multithread.synchronizer;

import com.avalon.ms.multithread.constants.ThreadConstants;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月21日 下午3:38:56
 *@version
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	public SleepyBoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	
	public void put(V v) throws InterruptedException{
		while(true){
			synchronized (this) {
				if(!isFull()){
					doPut(v);
					return;
				}
			}
			Thread.sleep(ThreadConstants.SLEEP_GRANULARITY.getValue());
		}
	}
	
	public V take() throws InterruptedException{
		while(true){
			synchronized (this) {
				if(!isEmpty())
					return doTake();
			}
			Thread.sleep(ThreadConstants.SLEEP_GRANULARITY.getValue());
		}
	}
}
