package com.example.email;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailImpl implements Email {
	
	@Autowired
	private JavaMailSender sender;

	@Override
	@Async
	public void sendEmailAttachment(String toAddress, String[] toCc, String subject, String body, String filePath) {
		MimeMessage message = sender.createMimeMessage();
		Date now = new Date();
		LocalDateTime dateTime = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(toAddress);
			helper.setSentDate(now);
			helper.setCc(toCc);
			helper.setSubject(subject);
			helper.setText(body);
			helper.addAttachment("Filename-"+dateTime.toString(), new File(filePath));	
			sender.send(message);
			System.out.println("Email sent successfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendEmail(String toAddress, String[] toCc, String subject, String body) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			
			helper.setTo(toAddress);
			helper.setCc(toCc);
			helper.setSubject(subject);
			helper.setText(body);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		sender.send(message);

	}

}
