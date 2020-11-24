package com.avalon.ms.multithread;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月13日 下午3:10:07
 *@version
 */
public class BoundedHashSet<T> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Set<T> set;
	private final Semaphore sem;
	
	public BoundedHashSet(int bound){
		this.set = Collections.synchronizedSet(new HashSet<T>());
		sem = new Semaphore(bound);
	}
	
	public boolean add(T o) throws InterruptedException{
		sem.acquire();
		boolean wasAdded =false;
		try{
			logger.info("add:"+o);
			System.out.println("thread:"+Thread.currentThread().getName()+" add "+o);
			wasAdded = set.add(o);
			return wasAdded;
		}
		finally{
			if(!wasAdded)
				sem.release();
		}
	}
	
	public int size(){
		return this.set.size();
	}
	
	public boolean remove(Object o){
		logger.info("removed:"+o);
		System.out.println("thread:"+Thread.currentThread().getName()+" remove "+o);
		boolean wasRemoved = set.remove(o);
		if(wasRemoved)
			sem.release();
		return wasRemoved;
	}
	
	public static void main(String[] args) {
		BoundedHashSet<Integer> bhs = new BoundedHashSet<Integer>(10);
		/*for(int i=0;i<20;i++){
			try {
				bhs.add(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<20;i++){
					try {
						bhs.add(i);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				for(int i=0;i<10;i++){
					bhs.remove(i);
				}
			}
		}).start();
		
		System.out.println(bhs.size());
	}
}
