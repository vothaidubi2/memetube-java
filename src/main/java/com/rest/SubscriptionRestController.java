package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Subscribe;
import com.entity.Video;
import com.service.SubscriptionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SubscriptionRestController {
	@Autowired 
	SubscriptionService service;
	@GetMapping("/getSubscription")
	public ResponseEntity<List<Video>> getSubInfo(@RequestParam int iduser) {
		return ResponseEntity.ok(service.getSubscription(iduser));
	}
}
