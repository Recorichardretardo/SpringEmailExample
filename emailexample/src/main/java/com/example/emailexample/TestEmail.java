package com.example.emailexample;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEmail {

	private EmailService emailService;

	@Autowired
	public TestEmail(EmailService emailService) {
		this.emailService = emailService;
	}

	@RequestMapping("/send")
	public String sendMail() {
		String response;
		String firstName = "First Name";
		String password = "Password";
		String email = "springemail8@gmail.com";
		
		 try {
			emailService.sendNewPasswordEmail(firstName, password, email);
			response = "Mail sent";
		} catch (MessagingException e) {
			response = "Mail sending failed";
			e.printStackTrace();
		}
		return response;
	}

}
