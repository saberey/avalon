package com.avalon.ms.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月1日 下午4:09:02
 *@version
 */
public class BaseDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public SqlSessionTemplate getSessionTemplate(){
		return sqlSessionTemplate;
	}
}
