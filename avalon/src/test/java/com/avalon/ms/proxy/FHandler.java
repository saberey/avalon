package com.avalon.ms.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *@description:TODO
 *@author saber
 *@date 2018年2月6日 下午1:29:58
 *@version
 */
public class FHandler implements InvocationHandler {
	
	private Object target;
	public FHandler(Object obj){
		this.target = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
			System.out.println("fhandler m dobefore!");
		try{
			return method.invoke(target, args);
		}finally{
			System.out.println("fhandler m doafter!");
		}
	}

}
