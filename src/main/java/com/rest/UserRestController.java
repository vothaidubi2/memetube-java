package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Users;
import com.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserRestController {
	@Autowired
	UserService userService;
	
	@GetMapping("/getuserbyemail")
	public ResponseEntity<Users> getByEmail(@RequestParam String email) {
		return ResponseEntity.ok(userService.getByEmail(email));
	}
}
