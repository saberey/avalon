package com.avalon.ms.common.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.avalon.ms.common.bean.MailContact;
import com.avalon.ms.common.bean.MailInfo;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月10日 上午10:41:09
 *@version
 */
public class SimpleEmailSender extends BaseEmailSender {
	
	
	@Override
	public void sendEmail(MailContact mailContact, MailInfo mailInfo,
			Properties prop) {
		// TODO Auto-generated method stub
		Authenticator  authenticator  = null;
		if(prop.getProperty(EmailProperties.MAIL_SMTP_AUTH).equals("true")){
			authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(mailContact.getSender(), mailContact.getSenderPwd());
				}
			};
		}
		
		Session session = Session.getInstance(prop, authenticator);
		Message message = new MimeMessage(session);
		String senderAlias ="";
		try {
			senderAlias = MimeUtility.encodeText(mailContact.getSenderAlias());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sender = senderAlias+"<"+mailContact.getSender()+">";
		try {
			message.setFrom(new InternetAddress(sender));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailContact.getTo()));
			message.setSubject(mailInfo.getSubject());
			message.setContent(mailInfo.getContent(), "text/html;charset=utf-8");
			
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
