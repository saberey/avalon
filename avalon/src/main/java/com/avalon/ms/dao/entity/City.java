package com.avalon.ms.dao.entity;

import com.avalon.ms.dao.mybatis.enums.CityAliasEnums;


public class City {

	private int id;
	private String city;
	private CityAliasEnums code2;
	private String province;
	private Province cityProvince;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public CityAliasEnums getCode2() {
		return code2;
	}
	public void setCode2(CityAliasEnums code) {
		this.code2 = code;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Province getCityProvince() {
		return cityProvince;
	}
	public void setCityProvince(Province cityProvince) {
		this.cityProvince = cityProvince;
	}
	
	public String toString(){
		return "city["+this.id+"|"+this.city+"|"+this.code2+"|"+this.province+"]";
	}
}
