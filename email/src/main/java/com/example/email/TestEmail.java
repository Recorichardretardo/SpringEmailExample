package com.example.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  

@RestController
public class TestEmail {
	
	@Autowired
	EmailImpl email;
	
	@RequestMapping("/send")  
	public String sendMail() {  
		String response;
	try {
		email.sendEmail("rr1433885@gmail.com", "Spring Boot Test Email", "Spring Boot Test Mail Body sent ");
		response = "Mail sent";
	}catch(Exception e) {
		response = "Mail sending failed";
		System.out.println(e.getMessage());
	}
	
	return response;  
	} 

}
