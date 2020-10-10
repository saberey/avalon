package com.avalon.ms.common.mail;

import com.avalon.ms.common.mail.enums.EmailSenderTypeEnums;
import com.avalon.ms.common.util.ConfigUtil;

import java.util.Properties;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月10日 下午1:45:10
 *@version
 */
public class EmailProperties {
	
	//base constants
	public static final String  MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	public static final String  MAIL_SMTP_HOST = "mail.smtp.host";
	public static final String  MAIL_SMTP_PORT = "mail.smtp.port";
	public static final String  MAIL_SMTP_AUTH = "mail.smtp.auth";
	public static final String  MAIL_SMTP_TIMEOUT	= "mail.smtp.timeout";
	//private final String  
	
	//163 smtp
	private static final String  NETEASE_SMTP_TRANSPORT_PROTOCOL = ConfigUtil.getProperty("config/email-config.properties", 
			"netease.mail.smtp.transport.protocol");
	private static final String  NETEASE_SMTP_HOST =  ConfigUtil.getProperty("config/email-config.properties", 
			"netease.mail.smtp.host");
	private static final String  NETEASE_SMTP_PORT =  ConfigUtil.getProperty("config/email-config.properties", 
			"netease.mail.smtp.port");
	private static final String  NETEASE_SMTP_AUTH =  ConfigUtil.getProperty("config/email-config.properties", 
			"netease.mail.smtp.auth");
	private static final String  NETEASE_SMTP_TIMEOUT =  ConfigUtil.getProperty("config/email-config.properties", 
			"netease.mail.smtp.timeout");
	
	public static Properties initNetEaseSmtp(){
		Properties prop = new Properties();
		prop.setProperty(MAIL_TRANSPORT_PROTOCOL, NETEASE_SMTP_TRANSPORT_PROTOCOL);
		prop.setProperty(MAIL_SMTP_HOST, NETEASE_SMTP_HOST);
		prop.setProperty(MAIL_SMTP_PORT, NETEASE_SMTP_PORT);
		prop.setProperty(MAIL_SMTP_AUTH, NETEASE_SMTP_AUTH);
		prop.setProperty(MAIL_SMTP_TIMEOUT, NETEASE_SMTP_TIMEOUT);
		return prop;
	}
	
	public static Properties getProperties(EmailSenderTypeEnums emailSenderTypeEnums){
		//Properties
		switch (emailSenderTypeEnums.getType()) {
		case 1:
			return initNetEaseSmtp();
			//break;
		default:
			break;
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(NETEASE_SMTP_HOST);
	}
}
