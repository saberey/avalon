package com.avalon.ms.mybatis;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avalon.ms.dao.entity.Coursegrade;
import com.avalon.ms.dao.entity.Province;
import com.avalon.ms.dao.entity.Student;
import com.avalon.ms.dao.entity.StudentProcedureBean;
import com.avalon.ms.dao.mybatis.mapper.StudentMapper;
import com.avalon.ms.dao.mybatis.mapper.StudentMapper2;

public class MybatisTest {
	
	
	
	public static void main(String[] args) throws IOException {
	/*	String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlsession = sqlSessionFactory.openSession();*/
		PropertyConfigurator.configure("E:\\wangjw\\学习\\Ms\\Ms\\ms\\src\\main\\resources\\config\\log4j.properties");
		
	    Logger logger = Logger.getLogger(MybatisTest.class);
		logger.info("start!");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource.xml");
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
		SqlSession sqlsession = sqlSessionFactory.openSession();
		try {
			//通过命名空间进行查询时，命名空间需要与mapper文件定义的命名空间相同
			//City city = sqlsession.selectOne("com.avalon.ms.dao.mybatis.mapper.CityMapper.selectId",1);
			//通过接口进行访问，
			//CityMapper cityMapper = sqlsession.getMapper(CityMapper.class);
			//ProvinceMapper provinceMapper = sqlsession.getMapper(ProvinceMapper.class);
		//	Province province = provinceMapper.selectId(1);
			//Province province = sqlsession.selectOne("com.avalon.ms.dao.mybatis.mapper.ProvinceMapper.selectId",2);
			//List result = sqlsession.selectList(statement, parameter)
			//City  city= cityMapper.selectId(1);*/
			//City city = cityMapper.selectId(1);
			//List  argList = Arrays.asList(1,2);
			//List result = sqlsession.selectList("com.avalon.ms.dao.mybatis.mapper.ProvinceMapper.selectByIdList", argList);
			
			StudentMapper studentMapper = sqlsession.getMapper(StudentMapper.class);
			StudentProcedureBean spb = new StudentProcedureBean();
			spb.setName("wang");
			studentMapper.count(spb);
			System.out.println("return:"+spb.getCount());
			System.out.println("return:"+spb.getDate());
			
			/*List result = studentMapper.selectAll();*/
			//List result = sqlsession.selectList("com.avalon.ms.dao.mybatis.mapper.StudentMapper.selectAll");
			//List result = sqlsession.selectList("com.avalon.ms.dao.mybatis.mapper.CoursegradeMapper.selectByStudentNo",1);
			//List result = sqlsession.selectList("com.avalon.ms.dao.mybatis.mapper.CoursegradeMapper.selectAll");
			//List result = cityMapper.selectByIdList(argList);
			//StudentMapper studentMapper = sqlsession.getMapper(StudentMapper.class);
			//System.out.println(result);
			//StudentMapper2 sm2 = sqlsession.getMapper(StudentMapper2.class);
			//List result = sm2.selectAll();
			
			//lazyLoad test  
			/*List result = sqlsession.selectList("com.avalon.ms.dao.mybatis.mapper.StudentMapper.selectAll");*/
			/*for(int i=0;i<result.size();i++){
				Student student = (Student) result.get(i);
				System.out.println(student.toString());
				List cgList = student.getStudentCourseGradeList();
				TimeUnit.SECONDS.sleep(3);
				for(int j=0;j<cgList.size();j++){
					Coursegrade coursegrade = (Coursegrade) cgList.get(j);
					coursegrade.getCourse();
					TimeUnit.SECONDS.sleep(2);
				}
			}*/
			//lazyLoad test 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			sqlsession.close();
		}
		
	}
}
