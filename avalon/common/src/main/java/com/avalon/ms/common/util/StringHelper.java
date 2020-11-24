package com.avalon.ms.common.util;
/**
 *@descriptionTODO
 *@author saber
 *@date 2018年1月29日 上午10:19:28
 *@version
 */
public class StringHelper {

	public static String trimStart(String src,String replace){
		return src.startsWith(replace)?src.substring(replace.length()):src;
		
	}
	public static String trimEnd(String src,String replace){
		return src.endsWith(replace)?src.substring(0, src.length()-replace.length()):src;
	}
}
