package com.avalon.ms.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ReferenceTest {

	public static void main(String[] args) {
		
		final ReferenceQueue rq = new ReferenceQueue();
		String str = new String("test");
		
		WeakReference wf = new WeakReference(str, rq);
		//SoftReference srf = new SoftReference(str, rq);
		
		HashMap<String,Reference> hmap = new HashMap();
		hmap.put("wf", wf);
		//hmap.put("srf", srf);
		Thread t = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//super.run();
				try {
					Reference reference = rq.remove();
					System.out.println(reference +" removed");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		t.setDaemon(true);
		t.start();
		System.out.println("Reference Queue is listening!");
		str = null;
		System.out.println("Ready to gc");
		System.gc();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(wf.get());
		System.out.println("hmap loop");
		Set<Entry<String, Reference>> keys = hmap.entrySet();
		for(Entry<String, Reference> cur : keys){
			System.out.println(cur.getKey()+":"+cur.getValue());
		}
	}
}
