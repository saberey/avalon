package com.avalon.ms.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.avalon.ms.dao.entity.Student;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年10月18日 上午10:39:54
 *@version
 */
public interface StudentMapper2 {
	
	@Select(value = { " select id, student_no, name, sex, remark from student order by id asc" })
	public List<Student> selectAll();
	
}
