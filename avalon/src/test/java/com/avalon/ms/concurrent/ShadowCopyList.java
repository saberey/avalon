package com.avalon.ms.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月9日 上午10:10:45
 *@version
 */
public class ShadowCopyList {

	private List<String> sList = null;
	
	public ShadowCopyList(List slist){
		this.sList = slist;
	}
	
	public void add(String element){
		sList.add(element);
	}
	
	public void remove(String element){
		sList.remove(element);
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<100;i++){
			list.add(String.valueOf(i));
		}
		ShadowCopyList scl = new ShadowCopyList(list);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Iterator it = list.iterator();
				while(it.hasNext()){
					String curElement = (String) it.next();
					System.out.println(curElement);
					try {
						TimeUnit.SECONDS.sleep(1);
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
				// TODO Auto-generated method stub
				scl.remove("1");
			}
		}).start();
	}
}
