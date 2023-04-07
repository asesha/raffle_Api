package com.alkimi.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.alkimi.vo.MailRequest;


@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Autowired
	Environment env;

	@Override
	public void sendMailWithAttachment(MailRequest request)  throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(env.getProperty("spring.mail.username"));
			helper.setTo(request.getEmailTo());
			helper.setSubject(request.getSubject());
			helper.setText(request.getMessage());
			helper.addAttachment(request.getFileResource().getFilename(), request.getFileResource());
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMailSender.send(message);
	}

	@Override
	@Async
	public void sendMail(MailRequest mail, String emailType) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(
				message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		Context context = new Context();
		context.setVariables(mail.getModel());
		String html = null;
		System.out.println("Email Type is " + emailType);
		if( "orderConfirmation".equalsIgnoreCase(emailType) ) {
			html = templateEngine.process("orderConfirmation", context);
		} else {
			html = templateEngine.process("email-template", context);
		}

		helper.setTo(mail.getEmailTo());
		helper.setText(html, true);
		helper.setSubject(mail.getSubject());
		//helper.setFrom("balachandar.nadar@alkimi.org");
		helper.setFrom("contact@alkimi.com");
		
		javaMailSender.send(message);
	}
}
