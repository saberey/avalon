package com.avalon.ms.aop.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 *@description:TODO
 *@author saber
 *@date 2017年10月23日 下午3:46:16
 *@version
 */
public class HServiceCglib implements MethodInterceptor {
	
	private Object target;
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		System.err.println(" 准备");
		Object returnObj = proxy.invokeSuper(target, args);
		System.err.println(" 结束");
		return returnObj;
	}
}
