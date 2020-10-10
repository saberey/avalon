package com.avalon.ms.dao.mybatis.enums;

/**
 *@description:TODO
 *@author saber
 *@date 2017年10月9日 下午1:18:24
 *@version
 */
public enum SexEnums {

	MALE(1,"男","male"),
	FEMALE(2,"女","female");

	private int type;
	private String desc_cn;
	private String desc;
	
	private SexEnums(int type,String desc_cn,String desc){
		this.type = type;
		this.desc_cn = desc_cn;
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public String getDesc_cn() {
		return desc_cn;
	}

	public String getDesc() {
		return desc;
	}
	
	public String toString(){
		return "["+type+","+desc_cn+","+desc+"]";
	}
	
	public static SexEnums getSexEnums(int type){
		if(type==1){
			return MALE;
		}else if(type==2){
			return FEMALE;
		}else{
			return null;
		}
	}
}
