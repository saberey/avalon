package com.avalon.ms.common.listener;

import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月2日 下午2:36:54
 *@version
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{
	
	/**
	 * 打印配置了指定注解的bean,测试时未能成功打印，待确认
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		if(event.getApplicationContext().getParent()==null){
			Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(Component.class);
			for(Object bean : beans.values()){
				System.err.println("**************contextRefresh************"+(bean==null?"null":bean.getClass().getName()));
			}
			System.err.println("_______________contextRefreshListener___________________");
		}
	}
}
