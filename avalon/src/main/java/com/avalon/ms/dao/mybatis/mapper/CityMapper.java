package com.avalon.ms.dao.mybatis.mapper;

import java.util.List;

import com.avalon.ms.dao.entity.City;

public interface CityMapper {

	public void insertRow(City city);
	
	public City selectId(int id);
	
	public List<City> selectByIdList(List list);
	
	public City selectByCode(String code);
	
	public List<City> selectByAll();
	
	public void insertSelect(City city);

}
