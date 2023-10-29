package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.CreatePayment;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class MoMoRestController {
	@Autowired
	CreatePayment payment;
	
	@PostMapping("/createpayment")
	public ResponseEntity<String> createPayment(@RequestParam long amount,@RequestParam String iduser) throws Exception{
		return ResponseEntity.ok(payment.createPayment(amount,iduser));
	}
}
