package com.avalon.ms.mybatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.avalon.ms.dao.entity.TFile;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年10月30日 下午5:55:19
 *@version
 */
public class TfileTest {

	public static void main(String[] args) throws FileNotFoundException {
		
		PropertyConfigurator.configure("E:\\wangjw\\学习\\Ms\\Ms\\ms\\src\\main\\resources\\config\\log4j.properties");
		Logger logger = Logger.getLogger(MybatisTest.class);
		logger.info("start!");
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/datasource.xml");
		SqlSessionFactory sessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");
		
		//setFile(sessionFactory);
		getFile(sessionFactory);
	}
	
	private static void getFile(SqlSessionFactory sessionFactory){
		SqlSession session = sessionFactory.openSession();
		List<TFile> tFileList = session.selectList("com.avalon.ms.dao.mybatis.mapper.TFileMapper.selectAll");
		session.commit();
		for(TFile curFile : tFileList){
			System.out.println(curFile.getFile().length);
		}
	}
	
	private static void setFile(SqlSessionFactory sessionFactory)
			throws FileNotFoundException {
		SqlSession session = sessionFactory.openSession();
		File file = new File("E:\\160406\\kpdp\\src\\main\\java\\com\\kaiyuan\\bank\\api\\AccountCollector.java");
		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		TFile tFile = new TFile();
		tFile.setFile(bytes);
		
		session.insert("com.avalon.ms.dao.mybatis.mapper.TFileMapper.insert", tFile);
		session.commit();
	}
	
	
	

}
