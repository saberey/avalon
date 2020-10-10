package com.avalon.ms.common.util;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月8日 上午11:58:26
 *@version
 */
public class TimeOperatorUtil {

	public static LocalDateTime getLocalDateTimeNow(){
		return LocalDateTime.now();
	}
	
	public static long getDuration(LocalDateTime start,LocalDateTime end){
		return Duration.between(start, end).toMillis();
	}
}
