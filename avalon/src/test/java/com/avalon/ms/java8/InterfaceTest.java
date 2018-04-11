package com.avalon.ms.java8;

import java.util.function.Supplier;

/**
 *@description:TODO
 *@author saber
 *@date 2017年9月25日 上午10:44:03
 *@version
 */
public class InterfaceTest {

	private interface Defaulable{
		default String notRequired(){
			return "Default implementation";
		}
	}
	
	private static class DefaultableImpl implements Defaulable{
		
	}
	
	private static class OverridableImpl implements Defaulable{
		@Override
		public String notRequired(){
			return "Overriden implemention";
		}
	}
	
	private interface DefaulableFactory{
		static Defaulable create(Supplier<Defaulable> supplier){
			return supplier.get();
		}
	}
	
	public static void main(String[] args) {
		Defaulable defaulable  = DefaulableFactory.create(DefaultableImpl :: new);
		System.out.println(defaulable.notRequired());
		
		defaulable = DefaulableFactory.create(OverridableImpl :: new);
		System.out.println(defaulable.notRequired());
	}
}
