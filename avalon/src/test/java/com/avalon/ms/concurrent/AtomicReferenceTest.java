package com.avalon.ms.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月8日 下午4:13:44
 *@version
 */
public class AtomicReferenceTest {
	
	
	
	
	private static class IntPair{
		final int lower;
		final int upper;
		public IntPair(int lower,int upper){
			this.lower = lower;
			this.upper = upper;
		}
	}
	
	private final AtomicReference<IntPair> values = new AtomicReference<IntPair>(new IntPair(0,Integer.MAX_VALUE));
	
	public int getLower(){
		return values.get().lower;
	}
	
	public int getUpper(){
		return values.get().upper;
	}
	
	public void setLower(int i){
		while(true){
			IntPair oldv = values.get();
			if(i>oldv.upper)
				throw new IllegalArgumentException("not set lower to "+i+" > upper ");
			IntPair newv = new IntPair(i,oldv.upper);
			if(values.compareAndSet(oldv, newv))
				return;
		}
	}
	
	public void setUpper(int i){
		while(true){
			IntPair oldv = values.get();
			if(i<oldv.lower)
				throw new IllegalArgumentException("not set upper to +"+i+" < lower");
			IntPair newv = new IntPair(oldv.lower,i);
			if(values.compareAndSet(oldv, newv))
				return;
		}
	}
}
