package com.avalon.ms.multithread.synchronizer;

import java.util.concurrent.Semaphore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 *@description:TODO
 *@author saber
 *@date 2017年12月13日 下午5:12:45
 *@version
 */
@ThreadSafe
public class BoundedBuffer<E> {

	private final Semaphore availableItems,availableSpaces;
	@GuardedBy("this") private final E[] items;
	@GuardedBy("this") private int putPosition = 0,takePosition = 0;

	public BoundedBuffer(int capacity){
		availableItems = new Semaphore(0);
		availableSpaces = new Semaphore(capacity);
		items = (E[]) new Object[capacity];
	}

	public boolean isEmpty(){
		return availableItems.availablePermits() == 0;
	}

	public boolean isFull(){
		return availableSpaces.availablePermits() == 0;
	}

	public void put(E x) throws InterruptedException{
		availableSpaces.acquire();
		doInsert(x);
		availableItems.release();
	}

	public E take() throws InterruptedException{
		availableItems.acquire();
		E item = doExtract();
		availableSpaces.release();
		return item;
	}

	private synchronized E doExtract() {
		// TODO Auto-generated method stub
		int i = takePosition;
		E x = items[i];
		takePosition =(++i == items.length)?0:i;
		return x;
	}

	private synchronized void doInsert(E x) {
		// TODO Auto-generated method stub
		int i = putPosition;
		items[i] = x;
		putPosition = (++i == items.length)?0:i;
	}
}
