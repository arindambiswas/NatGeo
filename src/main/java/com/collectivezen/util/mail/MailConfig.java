package com.collectivezen.util.mail;

public class MailConfig
{
	private String smtpServer;
	private String username;
	private String password;
	
	
	public MailConfig(String smtpServer, String username,
			String password)
	{
		super();
		this.smtpServer = smtpServer;
		this.username = username;
		this.password = password;
	}
	
	public String getSmtpServer()
	{
		return smtpServer;
	}
	public void setSmtpServer(String smtpServer)
	{
		this.smtpServer = smtpServer;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}
