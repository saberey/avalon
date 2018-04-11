package com.avalon.ms.dao.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avalon.ms.dao.entity.City;
import com.avalon.ms.dao.service.CityService;

@Component
public class CityServiceImpl implements CityService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public City selectById(int id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.avalon.ms.dao.mybatis.mapper.CityMapper.selectId", id);
	}

	@Override
	public List<City> selectByIdList(List list) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.avalon.ms.dao.mybatis.mapper.CityMapper.selectByIdList", list);
	}

	@Override
	public void insert(City city) {
		// TODO Auto-generated method stub
		sqlSession.insert("com.avalon.ms.dao.mybatis.mapper.CityMapper.insertRow",city);
	}

	@Override
	public City selectByCode(String code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.avalon.ms.dao.mybatis.mapper.CityMapper.selectByCode",code);
	}

	@Override
	public List<City> selectByAll(City city) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.avalon.ms.dao.mybatis.mapper.CityMapper.selectByAll",city);
	}

	@Override
	public void insertSelect(City city) {
		// TODO Auto-generated method stub
		sqlSession.insert("com.avalon.ms.dao.mybatis.mapper.CityMapper.insertSelect", city);
	}
}
