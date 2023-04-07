package com.alkimi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceJavaMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class MailConfig {

	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService(AWSCredentialsProvider provider,
			@Value("${cloud.aws.region.static}") String region) {
		return AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(provider).withRegion(region).build();
	}

	@Bean
	public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
		return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
	}
}
