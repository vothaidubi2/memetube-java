package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Users;
import com.service.UserService;
import com.service.WalletService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class WalletRestController {
	@Autowired
	WalletService walletService;
	@Autowired
	UserService userService;

	@PutMapping("/updatebalance")
	public ResponseEntity<HttpStatus> updateBalance(@RequestParam Double balance, @RequestParam int iduser) {
		if(walletService.findByidUser(iduser) !=null) {
			walletService.updateBalance(balance, iduser);
			return ResponseEntity.ok().build();			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/findUserById")
	public ResponseEntity<Users> findByIdUser(@RequestParam int iduser) {
		if(walletService.findByidUser(iduser) !=null) {
			return ResponseEntity.ok(walletService.findByidUser(iduser));			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
