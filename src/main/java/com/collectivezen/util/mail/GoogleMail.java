package com.collectivezen.util.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class GoogleMail extends Mail
{

	public static void sendMail(MailContent mailContent, MailConfig mailConfig)
	{
		try
		{
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "smtp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.quitwait", "false");

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication("username", "password");
				}
			});

			MimeMessage message = new MimeMessage(session);
			InternetAddress internetAddress = new InternetAddress(mailContent.getSender());
			message.setSender(internetAddress);
			message.setSubject(mailContent.getSubject());
			message.setContent(mailContent.getBody(), "text/html");
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailContent.getRecipents()));

			// Send message with authentication!
			Transport tr = session.getTransport("smtp");
			tr.connect(mailConfig.getSmtpServer(), mailConfig.getUsername(), mailConfig.getPassword());
			message.saveChanges();
			tr.sendMessage(message, message.getAllRecipients());
			tr.close();
		}
		catch (Exception e)
		{
			System.out.println("GoogleMail.sendMail()");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println("GoogleMail.main()");
		
		String mailBody = "Hi Arindam, <br />";
		mailBody += "Thank you for participating in the Mission Army Contest!<br />";
		mailBody += "Please follow the contest on facebook <br />";
		mailBody += "Thanks, <br />";
		mailBody += "NatGeo India";
		
		MailContent mailContent = new MailContent("natgeo.feedback@gmail.com", "arindam@codedrunks.com", "Test Subject", mailBody);
		MailConfig mailConfig = new MailConfig("smtp.gmail.com", "natgeo.feedback@gmail.com", "kalkik4lk1");
		
		GoogleMail.sendMail(mailContent, mailConfig);
		
		System.out.println("GoogleMail.main() ... mail sent!");
	}
}
