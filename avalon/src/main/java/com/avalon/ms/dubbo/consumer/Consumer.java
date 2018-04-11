package com.avalon.ms.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avalon.ms.dubbo.demo.DemoService;

/**
 *@description:TODO
 *@author saber
 *@date 2017年12月4日 下午5:32:18
 *@version
 */
public class Consumer {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:config/dubbo-demo-consumer.xml");
		DemoService demoService = (DemoService) context.getBean("demoService");
		String words = demoService.sayHello("test");
		System.out.println(words);
	}
}
