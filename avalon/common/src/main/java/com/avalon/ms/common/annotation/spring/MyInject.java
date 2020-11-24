package com.avalon.ms.common.annotation.spring;

import java.lang.annotation.*;


/**
 *@descriptionTODO
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
