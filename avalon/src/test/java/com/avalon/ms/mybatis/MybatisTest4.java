package com.avalon.ms.mybatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avalon.ms.dao.entity.Student;
import com.avalon.ms.dao.mybatis.enums.SexEnums;
import com.avalon.ms.dao.service.StudentService;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月1日 下午4:59:28
 *@version
 */
public class MybatisTest4 {

	public static void main(String[] args) {
		PropertyConfigurator.configure("E:\\wangjw\\学习\\Ms\\Ms\\ms\\src\\main\\resources\\config\\log4j.properties");
		
	    Logger logger = Logger.getLogger(MybatisTest3.class);
		logger.info("start!");
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/datasource.xml");
		
		final StudentService service = (StudentService) ac.getBean(StudentService.class);
		StudentService service2 = (StudentService) ac.getBean(StudentService.class);
		
		Thread thread1 = new Thread(new  Runnable() {
			public void run() {
				Student student = new Student();
				student.setId(17);
				student.setName("test5");
				student.setStudentNo("17");
				student.setSex(SexEnums.MALE);
				student.setRemark("1");
				service.addStudent(student);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<Student> studentList = service2.getAllStudent();
			}
		});
		
		Thread thread3 = new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						try {
							thread1.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// TODO Auto-generated method stub
						List<Student> studentList = service2.getAllStudent();
					}
				});
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
