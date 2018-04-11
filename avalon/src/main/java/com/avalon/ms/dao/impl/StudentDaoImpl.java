package com.avalon.ms.dao.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Repository;

import com.avalon.ms.dao.BaseDao;
import com.avalon.ms.dao.StudentDao;
import com.avalon.ms.dao.entity.Student;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月1日 下午4:07:27
 *@version
 */
@Repository
public class StudentDaoImpl extends BaseDao implements StudentDao {

	@Override
	public void insert(Student student) {
		// TODO Auto-generated method stub
		//getSessionTemplate().
		getSessionTemplate().insert("com.avalon.ms.dao.mybatis.mapper.StudentMapper.insert", student);
	}

	@Override
	public List<Student> selectAll() {
		// TODO Auto-generated method stub
		return getSessionTemplate().selectList("com.avalon.ms.dao.mybatis.mapper.StudentMapper.selectAll");
	}
}
