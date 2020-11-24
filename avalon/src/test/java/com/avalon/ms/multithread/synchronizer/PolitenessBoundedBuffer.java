package com.avalon.ms.multithread.synchronizer;
/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月21日 下午3:55:15
 *@version
 */
public class PolitenessBoundedBuffer<V> extends BaseBoundedBuffer<V>{

	public PolitenessBoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public synchronized void put(V v) throws InterruptedException{
		while(isFull())
			wait();
		doPut(v);
		notifyAll();
	}
	
	public synchronized V take() throws InterruptedException{
		while(isEmpty())
			wait();
		V v = doTake();
		notifyAll();
		return v;
	}
}
