package com.avalon.ms.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月9日 上午10:05:25
 *@version
 */
public class ShadowCopyTest {

	private final HashMap<String,String> hm ;
	
	public ShadowCopyTest(Map<String, String> map){
		this.hm=(HashMap<String, String>) map;
	}
	
	public synchronized  void add(String key,String value){
		hm.put(key, value);
	}
	
	public synchronized String get(String key){
		return hm.get(key);
	}
	
	public static void main(String[] args) {
		
	}
}
