package com.avalon.ms.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月7日 上午11:18:26
 *@version
 */
@Component
public class GlobalAPI implements BeanFactoryAware{
	
	private BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		this.beanFactory = beanFactory;
	}

	public void init(){
		AopService as1 = beanFactory.getBean(AopService.class);
		as1.init(1, "as1");
		as1.service();
		
		AopService as2 = beanFactory.getBean(AopService.class);
		as2.init(2, "as2");
		as2.service();
	}
}
