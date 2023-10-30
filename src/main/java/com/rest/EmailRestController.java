package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EmailDetails;
import com.service.EmailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class EmailRestController {
	@Autowired
	EmailService emailService;
	
	@PostMapping("/sendemail")
	public ResponseEntity<HttpStatus> sendMail(@RequestBody EmailDetails details) {
		return emailService.sendSimpleMail(details);
	}
}
