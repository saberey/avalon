package com.avalon.ms.common.support;

import java.util.Date;


/**
 * column type  and default value
 * @description:TODO
 * @author: saber
 * @time: 2017年7月25日 下午5:33:06
 * @version 1.0
 */
public enum ColumnTypeEnum {

	CHAR("char",50),
	VARCHAR("varchar",50),
	TINYTEXT ("tinytext",60),
	TEXT ("text",200),
	TINYINT  ("tinyint",3),
	SMALLINT ("smallint",4),
	MEDIUMINT("mediumint",8),
	INT("int",16),
	BIGINT ("bigint",32),
	FLOAT ("float",8),
	DOUBLE ("double",10),
	DECIMAL ("decimal",8),
	Date("date",10),
	Time("time",8),
	YEAR ("year",4),
	DateTime("datetime",19),
	TimeStamp("timestamp",10)
	;
	
	private String name;
	private int value;
	private ColumnTypeEnum(String name,int value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static  ColumnTypeEnum getColumnType(Class<?> columnType){
		if(columnType == String.class){
			return ColumnTypeEnum.VARCHAR;
		}else if(columnType == Integer.class){
			return ColumnTypeEnum.INT;
		}else if(columnType == Float.class){
			return ColumnTypeEnum.FLOAT;
		}else if(columnType == Date.class){
			return ColumnTypeEnum.TimeStamp;
		}else{
			return ColumnTypeEnum.VARCHAR;
		}
	}
}
