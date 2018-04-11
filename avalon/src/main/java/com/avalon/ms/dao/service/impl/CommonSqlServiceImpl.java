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
import com.avalon.ms.dao.service.CommonSqlService;

@Service
public class CommonSqlServiceImpl implements CommonSqlService {


	@Autowired
	private SqlSession sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(MsOperatorServiceImpl.class);
	
	@Override
	public int getTotalCount(MySqlTableName tablNameEum) {
		// TODO Auto-generated method stub
		//查询总数
		int  totalCount = sqlSession.selectOne("com.avalon.ms.dao.mybatis.mapper.CommonSqlMapper.count", MySqlTableName.MSOPERATOR.getTableName());
		logger.info("query table {} total count {}", MySqlTableName.MSOPERATOR);
		return totalCount;
	}
}
