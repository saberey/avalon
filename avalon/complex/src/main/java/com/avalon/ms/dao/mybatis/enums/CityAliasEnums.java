package com.avalon.ms.dao.mybatis.enums;

public enum CityAliasEnums {

	BJ("beijing","1"),
	HB("hebei","201"),
	SD("shandong","301");
	
	private String city;
	private String  code;
	
	private CityAliasEnums(String city,String code){
		this.city = city;
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static CityAliasEnums getCityAlias(String code){
		System.out.println("getCityAlias:"+code);
		if(code.equals("1")){
			return BJ;
		}else if(code.equals("201")){
			return HB;
		}else if(code.equals("301")){
			return SD;
		}else{
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(CityAliasEnums.getCityAlias("201"));
	}
}
