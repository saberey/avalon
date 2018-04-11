package com.avalon.ms.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

	private static Properties config = null;
	
	public ConfigUtil(){
		config = new Properties();
	}
	public ConfigUtil(String filePath){
		config = getProperty(filePath);
	}
	
	public String get(String key){
		return config.getProperty(key);
	}
	
	public static String getProperty(String filepath,String key){
		return getProperty(filepath).getProperty(key);
	}
	
	public static Properties getProperty(String filepath){
		Properties properties = new Properties();
		ClassLoader cl = ConfigUtil.class.getClassLoader();
	    InputStream inputStream = cl.getResourceAsStream(filepath);
	    try {
	    	properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return properties;
	}
}
