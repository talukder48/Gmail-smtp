package MailService;
/************************************************************************************
 * Reference: (https://javapapers.com/core-java/java-email/)
 * Developed By Mosharraf 
 * Need 2 jar files smtp.jar,mailapi.jar
 * Allow less secure apps (ON) ( https://myaccount.google.com/lesssecureapps)  
 ***********************************************************************************/

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class BulkMailService {
	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	
	public static String MailService(String mailHeader,String mailBody,String UserMail,String UserPassword, String bulkMail[])throws AddressException, MessagingException {
		try {
			BulkMailService bulkmailService = new BulkMailService();
			bulkmailService.setMailServerProperties();
			bulkmailService.createEmailMessage(mailHeader, mailBody, bulkMail);
			bulkmailService.post(UserMail,UserPassword);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "Sucess";
	}
	private void setMailServerProperties() {
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", "587");
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		emailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");		 		
	}

	private void createEmailMessage(String EmailSubject, String EmailBody, String[] bulkMail)throws AddressException, MessagingException {
		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);
		for (int i = 0; i < bulkMail.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(bulkMail[i]));
		}
		emailMessage.setSubject(EmailSubject);
		emailMessage.setContent(EmailBody, "text/html");
		
		
	}

	private void post(String UserMail,String UserPassword) throws AddressException, MessagingException {
		String emailHost = "smtp.gmail.com";
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, UserMail, UserPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}
}
