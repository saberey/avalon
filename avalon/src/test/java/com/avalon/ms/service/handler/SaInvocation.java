package com.avalon.ms.service.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.avalon.ms.service.impl.Saa;

public class SaInvocation  implements InvocationHandler{
	
	private Saa sa;
	public SaInvocation(Saa sa){
		this.sa = sa;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("invocation");
		for(Object arg:args){
			System.out.println(arg);
		}
		Parameter[] params = method.getParameters();
		for(Parameter param : params){
			System.out.println(param.getName()+"|"+param.getType()+"|"+param.getParameterizedType());
		}
		return method.invoke(sa, args);
	}
	
	
}
