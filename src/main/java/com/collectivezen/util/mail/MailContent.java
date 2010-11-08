package com.collectivezen.util.mail;

public class MailContent
{
	private String sender;
	private String recipents;
	private String subject;
	private String body;
	
	public MailContent(String sender, String recipents, String subject, String body)
	{
		super();
		this.sender = sender;
		this.subject = subject;
		this.recipents = recipents;
		this.body = body;
	}
	
	public String getSender()
	{
		return sender;
	}
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	public String getRecipents()
	{
		return recipents;
	}
	public void setRecipents(String recipents)
	{
		this.recipents = recipents;
	}
	public String getBody()
	{
		return body;
	}
	public void setBody(String body)
	{
		this.body = body;
	}
	
	
}
