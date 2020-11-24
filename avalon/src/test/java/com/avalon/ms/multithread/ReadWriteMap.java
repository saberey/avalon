package com.avalon.ms.multithread;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月20日 下午3:57:49
 *@version
 */
public class ReadWriteMap<K,V> {

	private final Map<K,V> map;
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock r = lock.readLock();
	private final Lock w = lock.writeLock();
	
	public ReadWriteMap(Map<K,V> map){
		this.map = map;
	}
	
	public V put(K key,V value){
		w.lock();
		try{
			return map.put(key, value);
		}finally {
			w.unlock();
		}
	}
	
	public V remove(Object key){
		w.lock();
		try{
			return map.remove(key);
		}finally{
			w.unlock();
		}
	}
	
	public void putAll(Map<K,V> m){
		w.lock();
		try{
			 map.putAll(m);
		}finally{
			w.unlock();
		}
	}
	
	public void clear(){
		w.lock();
		try{
			map.clear();
		}finally{
			w.unlock();
		}
	}
	
	public V get(Object key){
		r.lock();
		try {
			return map.get(key);
		}finally{
			r.unlock();
		}
	}
	//对其他只读的Map方法执行读锁操作
}
