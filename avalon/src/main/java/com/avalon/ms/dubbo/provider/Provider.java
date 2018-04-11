package com.avalon.ms.dubbo.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *@description:TODO
 *@author saber
 *@date 2017年12月4日 下午5:18:27
 *@version
 */
public class Provider {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"classpath:config/dubbo-demo-provider.xml"});
		context.start();
		System.in.read();
	}
}
