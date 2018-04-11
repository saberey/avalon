package com.avalon.ms.dao.mybatis.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;

/**
 *@description:TODO
 *@author saber
 *@date 2017年10月10日 上午11:06:56
 *@version
 */
public class MCache implements Cache{

	private String id;
	private ConcurrentHashMap<Object, Object> mCacheCHM = new ConcurrentHashMap<Object, Object>();
	private ReadWriteLock readWriteLock;
	
	public MCache(){
		
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void putObject(Object key, Object value) {
		// TODO Auto-generated method stub
		mCacheCHM.putIfAbsent(key, value);
	}

	@Override
	public Object getObject(Object key) {
		// TODO Auto-generated method stub
		return mCacheCHM.get(key);
	}

	@Override
	public Object removeObject(Object key) {
		// TODO Auto-generated method stub
		return mCacheCHM.remove(key);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		mCacheCHM.clear();
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return mCacheCHM.size();
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return readWriteLock;
	}

}
