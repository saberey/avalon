package com.avalon.ms.dao;

import java.util.List;

import com.avalon.ms.dao.entity.Student;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月1日 下午4:07:49
 *@version
 */
public interface StudentDao {

	public void insert(Student student);
	
	public List<Student> selectAll();
}
