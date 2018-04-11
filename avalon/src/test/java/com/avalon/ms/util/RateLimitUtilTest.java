package com.avalon.ms.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.avalon.ms.common.util.RateLimitUtil;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月11日 下午5:46:12
 *@version
 */
public class RateLimitUtilTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		String limitName = "kpdpLimit";
		String limitKeyWord = "payment";
		int timeOut = 60;
		int max  = 100;
		for (int i = 0; i < 200; i++) {
			RateLimitUtil.incr(limitName, limitKeyWord, timeOut, max);
		}
	}
}
