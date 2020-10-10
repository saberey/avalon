package com.avalon.ms.common.annotation.table;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.avalon.ms.common.support.ColumnTypeEnum;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    /**
     * 表示字段名 默认为“”
     */
    String columnName() default "";

    /**
     * 表示字段长度 默认为0
     */
    String length() default "0";

    /**
     * 是否大字符串 true的话对于数据库里的TEXT类型 不要在意命名
     */
    boolean isBigString() default false;

    ColumnTypeEnum type() default ColumnTypeEnum.VARCHAR;
}
