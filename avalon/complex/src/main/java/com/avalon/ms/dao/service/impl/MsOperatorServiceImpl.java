package com.avalon.ms.dao.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avalon.ms.common.support.MySqlTableName;
import com.avalon.ms.dao.entity.BaseEntity;
import com.avalon.ms.dao.entity.MsOperator;
import com.avalon.ms.dao.service.MsOperatorService;

@Service
public class MsOperatorServiceImpl implements MsOperatorService {
	
	@Autowired
	private SqlSession sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(MsOperatorServiceImpl.class);
	
	@Override
	public void addUser(MsOperator msOperator) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.avalon.ms.dao.mybatis.mapper.MsOperatorMapper.insert", msOperator);
	}

	@Override
	public MsOperator getUserInfo(MsOperator msOperator) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.avalon.ms.dao.mybatis.mapper.MsOperatorMapper.selectByUser", msOperator);
	}

	@Override
	public List<MsOperator> getAllUser(MsOperator msOperator) {
		// TODO Auto-generated method stub
		System.out.println("msOperator:"+msOperator.getCurPageNo()+" "+msOperator.getPageSize());
		return sqlSession.selectList("com.avalon.ms.dao.mybatis.mapper.MsOperatorMapper.selectAll", msOperator);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		sqlSession.delete("com.avalon.ms.dao.mybatis.mapper.MsOperatorMapper.deleteUser", id);
	}

	@Override
	public int Update(MsOperator msOperator) {
		// TODO Auto-generated method stub
		return sqlSession.update("com.avalon.ms.dao.mybatis.mapper.MsOperatorMapper.update", msOperator);
	}
}
