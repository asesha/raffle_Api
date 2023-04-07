package com.alkimi.service;

import javax.mail.MessagingException;

import com.alkimi.vo.MailRequest;

public interface EmailService {

	public void sendMail(MailRequest request, String emailType)  throws MessagingException;
	public void sendMailWithAttachment(MailRequest request)  throws MessagingException;
	
}
