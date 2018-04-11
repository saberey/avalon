package com.avalon.ms.common.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

public class JsonUtil {

	
	public static<T> T json2bean(String jsonStr,Class<T> clazz){
		if(Strings.isNullOrEmpty(jsonStr)){
			return null;
		}
		T obj = JSONObject.parseObject(jsonStr, clazz);
		return obj;
	}
}
