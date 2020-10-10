package com.avalon.ms.common.annotation.spring;

import java.lang.annotation.*;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月18日 上午10:03:54
 *@version
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InjectTest {

	String value();
}
