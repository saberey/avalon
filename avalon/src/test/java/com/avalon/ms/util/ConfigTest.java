package com.avalon.ms.util;

import junit.framework.TestCase;

import org.junit.Test;
import com.avalon.ms.common.util.ConfigUtil;

public class ConfigTest extends TestCase {
	
	private static ConfigUtil config = null;
	@Test
	public void testConfigString() {
		//fail("Not yet implemented");
		config = new ConfigUtil("config/datasource.properties");
	}
	@Test
	public void testGet() {
		//fail("Not yet implemented");
		testConfigString();
		assertEquals("192.168.137.128", config.get("redis.address"));
	}
	
	@Test
	public void testGetProperties(){
		assertNotNull(ConfigUtil.getProperty("config/datasource.properties", "redis.address"));
	}
}
