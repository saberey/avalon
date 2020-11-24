package com.avalon.ms.common.annotation.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年1月18日 上午10:29:02
 *@version
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SelectTest {

	String[] value();
}
