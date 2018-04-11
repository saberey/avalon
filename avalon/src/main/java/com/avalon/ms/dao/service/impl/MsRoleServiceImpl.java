package com.avalon.ms.dao.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.avalon.ms.dao.entity.MsRole;
import com.avalon.ms.dao.service.MsRoleService;

@Service
public class MsRoleServiceImpl implements MsRoleService {
	
	@Autowired
	private SqlSession sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(MsRoleServiceImpl.class);
	
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	@Override
	public int insert(MsRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MsRole> selectAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.avalon.ms.dao.mybatis.mapper.MsRoleMapper.selectAll");
	}
}
