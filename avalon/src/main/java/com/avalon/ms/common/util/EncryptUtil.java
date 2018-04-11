package com.avalon.ms.common.util;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.jboss.netty.handler.codec.base64.Base64Encoder;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月2日 下午6:00:03
 *@version
 */
public class EncryptUtil {

	// MD5 SHA-1
	public static byte[] digest(String content,String algorithm) throws Exception{
		MessageDigest md = MessageDigest.getInstance(algorithm);
		byte[] bytes = md.digest(content.getBytes("utf-8"));
		return bytes;
	}
	
	public static byte[] MD5(String content) throws Exception{
		return digest(content, "MD5");
	}
	
	public static byte[] SHA1(String content) throws Exception{
		return digest(content,"SHA-1");
	}
	
	private static String byte2base64(byte[] bytes){
		//Base64Encoder base64Encoder = new Base64Encoder();
		//return base64Encoder.
		return "";
	}
	
	public static String genKeyDES() throws Exception{
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);
		SecretKey key = keyGen.generateKey();
		String base64Str = byte2base64(key.getEncoded());
		return base64Str;
	}
	
	public static void main(String[] args) {
		String input ="hello world!";
		try {
			System.out.println(EncryptUtil.MD5(input));
			System.out.println(EncryptUtil.SHA1(input));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
