package com.avalon.ms.proxy;

import java.lang.reflect.Proxy;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月6日 下午1:31:42
 *@version
 */
public class ProxyTest {

	public static void main(String[] args) {
		S s = new S();
		FHandler fHandler = new FHandler(s);
		F f = (F) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{F.class}, fHandler);
		f.say();
	}
}
