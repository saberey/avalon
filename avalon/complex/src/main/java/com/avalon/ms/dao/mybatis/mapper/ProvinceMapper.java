package com.avalon.ms.dao.mybatis.mapper;

import java.util.List;

import com.avalon.ms.dao.entity.Province;

public interface ProvinceMapper {

	public Province selectId(int id);
	
	public void insertRow(Province province);
	
	public List<Province> selectByIdList(List list);
}
