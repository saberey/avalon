package com.avalon.ms.common.util;
/**
 *@description:TODO
 *@author saber
 *@date 2017年11月9日 下午2:34:54
 *@version
 */
public class EmailUtil {

	    private static final String SMTP_HOST = ConfigUtil.getProperty("config.properties","mail.smtp.host");
	    private static final String SMTP_TRANSPORT_PROTOCOL = ConfigUtil.getProperty("config.properties","mail.transport.protocol");
	    private static final String SMTP_AUTH =  ConfigUtil.getProperty("config.properties","mail.smtp.auth");
	    private static final String SMTP_PORT = ConfigUtil.getProperty("config.properties","mail.smtp.port");
	    private static final String SMTP_TIMEOUT = ConfigUtil.getProperty("config.properties","mail.smtp.timeout");
	    private static final String EMAIL_SENDER = ConfigUtil.getProperty("config.properties","email.sender");
	    private static final String EMAIL_PWD = ConfigUtil.getProperty("config.properties","email.pwd");
	    
	    
}
