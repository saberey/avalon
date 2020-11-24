package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avalon.ms.dao.service.StudentService;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年1月8日 下午1:39:17
 *@version
 */
public class StudentServiceTest {

	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-mvc-dispatcher-servlet.xml");
		//StudentService ss = context.getBean(StudentServiceImpl.class);
		StudentService ss  = (StudentService) context.getBean("studentService");
		ss.getAllStudent();
	}
}
