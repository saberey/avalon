package test;

import java.util.concurrent.atomic.AtomicBoolean;

public class CASLock implements Lock{

	
	
	public static  AtomicBoolean aBoolean = new AtomicBoolean(false);
	
	public static void main(String[] args) {
//		System.out.println(aBoolean.get());
//		System.out.println(aBoolean.getAndSet(false));
//		System.out.println(aBoolean.getAndSet(true));
//		System.out.println(aBoolean.get());
		
		CASLock casLock = new CASLock();
		
		casLock.unLock();
		casLock.lock();
	}

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		while(aBoolean.getAndSet(true)){
			System.out.println("lock");
			System.out.println(11);
			aBoolean.set(false);
		}
		System.out.println("111");
	}

	@Override
	public void unLock() {
		// TODO Auto-generated method stub
		aBoolean.set(true);
		System.out.println(222);
	}
}

interface Lock{
	void lock();
	void unLock();
}