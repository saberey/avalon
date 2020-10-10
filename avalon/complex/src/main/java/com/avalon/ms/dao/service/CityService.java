package com.avalon.ms.dao.service;

import java.util.List;

import com.avalon.ms.dao.entity.City;

public interface CityService {

	public City selectById(int id);
	
	public List<City> selectByIdList(List list);
	
	public void insert(City city);
	
	public City selectByCode(String code);
	
	public List<City> selectByAll(City city);
	
	public void insertSelect(City city);
}
