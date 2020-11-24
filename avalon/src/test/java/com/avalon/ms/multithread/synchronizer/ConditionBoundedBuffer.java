package com.avalon.ms.multithread.synchronizer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月26日 下午5:35:47
 *@version
 */
public class ConditionBoundedBuffer<T> {

	protected  final Lock lock = new ReentrantLock();
	
	private final Condition notEmpty = lock.newCondition();
	private final Condition notFull  = lock.newCondition();
	
	private final int BUFFER_SIZE = 16;
	private final T[] items = (T[]) new Object[BUFFER_SIZE];
	
	private int tail,head,count;
	
	
	public void put(T t) throws InterruptedException{
		lock.lock();
		try{
			while(count==items.length)
				notFull.await();
			items[tail]=t;
			if(++tail == items.length)
				tail = 0;
			++count;
			notEmpty.signal();
		}finally{
			lock.unlock();
		}
	}
	
	public T take() throws InterruptedException{
		lock.lock();
		try{
			while(count==0)
				notEmpty.await();
			T t = items[head];
			items[head] = null;
			if(++head == items.length)
				head = 0;
			--count;
			notFull.signal();
			return t;
		}finally{
			lock.unlock();
		}
	}
}
