package com.avalon.ms.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年4月9日 下午1:15:11
 *@version
 */
public class TestService {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
		UserService userService = context.getBean(UserService.class);
		userService.say();
	}
}
