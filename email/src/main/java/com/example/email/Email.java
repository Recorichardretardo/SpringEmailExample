package com.example.email;

public interface Email {
	void sendEmailAttachment(String toAddress, String[] toCc, String subject, String body, String filePath);
	void sendEmail(String toAddress, String[] toCc, String subject, String body);	
}
