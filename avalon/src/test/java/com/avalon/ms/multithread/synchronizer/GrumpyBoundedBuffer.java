package com.avalon.ms.multithread.synchronizer;

import com.avalon.ms.multithread.exception.BufferEmptyException;
import com.avalon.ms.multithread.exception.BufferFullException;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月21日 下午3:27:30
 *@version
 * @param <V>
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	public GrumpyBoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void put(V v) throws BufferFullException{
		// TODO Auto-generated method stub
		if(isFull())
			throw new BufferFullException();
		doPut(v);
	}
	//这种做法不好，满或空不应是异常， 使用阻塞或等待通知
	public synchronized V take() throws BufferEmptyException{
		if(isEmpty())
			throw new BufferEmptyException();
		return doTake();
	}
}
