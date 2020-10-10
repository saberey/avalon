package com.avalon.ms.concurrent;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.jcip.annotations.GuardedBy;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月9日 下午1:16:07
 *@version
 */
public class HiddenIterator {
	
	@GuardedBy("this")
	private final Set<Integer> set = new HashSet<Integer>();
	
	public synchronized void add(Integer i){
		set.add(i);
	}
	public synchronized void remove(Integer i){
		set.remove(i);
	}
	
	public void addTenThings(){
		Random r = new Random();
		for(int i=0;i<1000000;i++)
			add(i);
		System.out.println("DEBUG: added ten elements to "+set);
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		HiddenIterator hi = new HiddenIterator();
		//输出set 调用toString()方法时会隐式的迭代集合，如果这是更改集合元素会抛出并发修改的错误
		//先往集合中添加元素（为了测试，添加足量元素，这样集合在迭代的时候会花费稍微长点时间，方便错误重现）
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				hi.addTenThings();
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<1000000;i++){
					hi.remove(i);
				}
			}
		}).start();
		Thread.sleep(30000);
	}
}
