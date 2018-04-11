package com.avalon.ms.common.mail;

import java.util.Properties;

import com.avalon.ms.common.bean.MailContact;
import com.avalon.ms.common.bean.MailInfo;
import com.avalon.ms.common.mail.enums.EmailSenderTypeEnums;
import com.avalon.ms.common.mail.enums.EmailTypeEnums;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月10日 上午10:22:45
 *@version
 */
public abstract class BaseEmailSender {
	
	private static BaseEmailSender emailSender = null;
	//初始化 邮件发送方式，通过邮件内容进行判断
	public static BaseEmailSender getInstance(EmailTypeEnums type){
		switch (type.getType()) {
		case 1:
			emailSender = new SimpleEmailSender();
			break;
		case 2:
			emailSender = new AttachEmailSender();
			break;
		default:
			break;
		}
		return emailSender;
	}
	
	//初始化邮件发送方参数  根据mailContact中发送者的类型判断
	public static Properties init(EmailSenderTypeEnums esteEmailSenderTypeEnums){
		return EmailProperties.getProperties(esteEmailSenderTypeEnums);
	}
	public static  void send(MailContact mailContact,MailInfo mailInfo){
		
		int senderType = mailContact.getSenderType();
		EmailSenderTypeEnums este = EmailSenderTypeEnums.getEmailSenderTypeEnums(senderType);
		
		int sendeType = mailInfo.getAttach();
		EmailTypeEnums ete = EmailTypeEnums.getEmailTypeEnums(sendeType);
		
		BaseEmailSender emailSender = getInstance(ete);
		emailSender.sendEmail(mailContact, mailInfo,init(este));
	}
	
	 public void sendEmail(MailContact mailContact,MailInfo mailInfo,Properties prop){
		 
	 }
}
