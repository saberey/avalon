package com.avalon.ms.dao.service;

import java.util.List;


import com.avalon.ms.dao.entity.Student;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月1日 下午3:55:24
 *@version
 */
public interface StudentService {

	public void addStudent(Student student);
	
	public List<Student> getAllStudent();
}
