package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import com.dto.EmailDetails;

@Service
public class EmailService{
	@Autowired private MailSender mailSender;

	@Value("${spring.mail.username}")
	private String sender;

	public ResponseEntity<HttpStatus> sendSimpleMail(EmailDetails details) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getTo());
			mailMessage.setText(details.getMessage());
			mailMessage.setSubject(details.getSubject());
			mailSender.send(mailMessage);
			return ResponseEntity.ok().build();
		}
		catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
