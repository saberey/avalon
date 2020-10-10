package com.avalon.ms.dao.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.avalon.ms.dao.StudentDao;
import com.avalon.ms.dao.entity.Student;
import com.avalon.ms.dao.service.StudentService;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月1日 下午3:55:43
 *@version
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		logger.info("增加学生 before");
		studentDao.insert(student);
		try {
			TimeUnit.SECONDS.sleep(10);
			logger.info("休眠10s");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("增加学生 end");
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_UNCOMMITTED,readOnly=true)
	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		logger.info("查询所有学生");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Student> list = studentDao.selectAll();
		logger.info("查询所有学生结束");
		return list;
	}
}
