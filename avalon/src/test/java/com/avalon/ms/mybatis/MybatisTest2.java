package com.avalon.ms.mybatis;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.data.redis.connection.util.DecodeUtils;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年10月18日 下午5:07:28
 *@version
 */
public class MybatisTest2 {

	private static final Class lock_class = MybatisTest.class;
	public static  SqlSessionFactory sqlSessionFactory;
	
	public static void main(String[] args) throws Exception {
		InputStream  cfgStream = null;
		Reader cfgReader = null;
		InputStream  proStream = null;
		Reader proReader = null;
		Properties properties = null;
		
		cfgStream = Resources.getResourceAsStream("config/mybatis-config.xml");
		cfgReader = new InputStreamReader(cfgStream);
		
		proStream = Resources.getResourceAsStream("config/mybatis-datasource.properties");
		proReader = new InputStreamReader(proStream);
		
		properties = new Properties();
		properties.load(proReader);
		//对读取的properties属性进行操作，下文模拟读取的数据时加密后的数据，进行解密后设置properties属性，
		//下文再根据config配置和properties创建sqlSessionFactory对象
		properties.setProperty("username", DecodeUtils.decode(properties.getProperty("username").getBytes()));
		properties.setProperty("password", DecodeUtils.decode(properties.getProperty("password").getBytes()));
		
		synchronized (lock_class) {
			if(sqlSessionFactory == null){
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(cfgReader, properties);
			}
		}
	}
}
