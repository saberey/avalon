package com.avalon.ms.mybatis;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avalon.ms.dao.entity.City;
import com.avalon.ms.dao.entity.Coursegrade;
import com.avalon.ms.dao.entity.Province;
import com.avalon.ms.dao.entity.Student;
import com.avalon.ms.dao.mybatis.mapper.CityMapper;
import com.avalon.ms.dao.mybatis.mapper.StudentMapper;
import com.avalon.ms.dao.mybatis.mapper.StudentMapper2;

public class MybatisTest3 {
	
	
	
	public static void main(String[] args) throws IOException {
		PropertyConfigurator.configure("E:\\wangjw\\学习\\Ms\\Ms\\ms\\src\\main\\resources\\config\\log4j.properties");
		
	    Logger logger = Logger.getLogger(MybatisTest3.class);
		logger.info("start!");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource.xml");
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
		
		//SqlSession sqlsession = sqlSessionFactory.openSession(ExecutorType.SIMPLE);
		//SqlSession sqlsession1 = sqlSessionFactory.openSession(ExecutorType.REUSE);
		//SqlSession sqlsession2 = sqlSessionFactory.openSession(ExecutorType.BATCH);
		SqlSession sqlsession2 = sqlSessionFactory.openSession();
		try {
			CityMapper cityMapper = sqlsession2.getMapper(CityMapper.class);
			List<City> result = cityMapper.selectByAll();
			for(City cur : result){
				System.out.println(cur);
			}
			sqlsession2.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			sqlsession2.close();
		}
		
	}
}
