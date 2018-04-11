package com.avalon.ms.common.mail.enums;
/**
 *@description:TODO
 *@author saber
 *@date 2017年11月10日 下午2:14:03
 *@version
 */
public enum EmailTypeEnums {

	SIMPLE(1),ATTACH(2);
	
	private EmailTypeEnums(int type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	private int type;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public static EmailTypeEnums getEmailTypeEnums(int type){
		switch (type) {
		case 1:
			return SIMPLE;
		case 2:
			return ATTACH;
		default:
			return null;
		}
	}
}
