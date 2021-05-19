package com.example.email;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

@RestController
public class TestEmail {

	@Autowired
	EmailImpl email;

	@Autowired
	PDFGenerator pdfGenerator;

	@GetMapping(path = "/generate-pdf")
	public String generatePDF() {
		String response;
		String filePath = "test.pdf";
		try {
			pdfGenerator.generatePDF(filePath);
			response = "PDF Generated";
		} catch (Exception e) {
			response = "PDF Generation failed";
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping("/send-email-attachment")
	public String sendMailAttachment() {
		String response;
		String toAddress = "rr1433885@gmail.com";
		String[] toCc = {};
		String subject = "Spring Boot Test Email";
		String body = "Spring Boot Test Please find attachment.";
		String filePath = "test.pdf";
		try {
			email.sendEmailAttachment(toAddress, toCc, subject, body, filePath);
			response = "Mail sent";
		} catch (Exception e) {
			response = "Mail sending failed";
			System.out.println(e.getMessage());
		}

		return response;
	}

	@RequestMapping("/send")
	public String sendMail() {
		String response;
		String toAddress = "rr1433885@gmail.com";
		String[] toCc = {};
		String subject = "Spring Boot Test Email";
		String body = "Spring Boot Test Mail Body sent ";
		try {
			email.sendEmail(toAddress, toCc, subject, body);
			response = "Mail sent";
		} catch (Exception e) {
			response = "Mail sending failed";
			System.out.println(e.getMessage());
		}

		return response;
	}

	@RequestMapping("/generate-pdf-send-email-attachment")
	public String generatePdfAndSendAsAttachment() {
		Date now = new Date();
		LocalDateTime dateTime = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());
		String response = "";
		String toAddress = "rr1433885@gmail.com";
		String[] toCc = {};
		String subject = "Spring Boot Test Email: "+dateTime.toString();
		String body = "Spring Boot Test Please find attachment.";
		String filePath = "test" + LocalDate.now().toString() + ".pdf";
		try {
			pdfGenerator.generatePDF(filePath);
			try {
				email.sendEmailAttachment(toAddress, toCc, subject, body, filePath);
				response = "Mail sent";
			} catch (Exception e) {
				response = "Mail sending failed";
				System.out.println(e.getMessage());
			}
		} catch (FileNotFoundException | DocumentException e1) {
			response = "PDF Generation Failed";
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}

		return response;
	}

}
