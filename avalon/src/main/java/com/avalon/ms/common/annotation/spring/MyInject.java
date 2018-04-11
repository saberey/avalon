package com.avalon.ms.common.annotation.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *@description:TODO
 *@author saber
 *@date 2018年1月9日 上午11:09:38
 *@version
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyInject {

	boolean required() default true;
	
}
