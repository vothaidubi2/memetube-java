package com.rest;

import java.util.List;

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
import com.entity.Wallet;
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

	@PutMapping("/depositmoney")
	public ResponseEntity<HttpStatus> depositBalance(@RequestParam int iduser,@RequestParam Double amount) {
		if (walletService.findByidUser(iduser) != null) {
			Double newbalance = walletService.findByidUser(iduser).getBalance() + amount;
			walletService.updateBalance(newbalance, iduser);
			return ResponseEntity.ok().build();
		} else {
			walletService.postBalance(iduser,amount);
			return ResponseEntity.ok().build();
		}
	}	@PutMapping("/updatemoney")
	public ResponseEntity<HttpStatus> updatemoney(@RequestParam int iduser,@RequestParam Double amount) {
		if (walletService.findByidUser(iduser) != null) {
			Double newbalance = amount;
			walletService.updateBalance(newbalance, iduser);
			return ResponseEntity.ok().build();
		} else {
			walletService.postBalance(iduser,amount);
			return ResponseEntity.ok().build();
		}
	}

	@PutMapping("/sendmoney")
	public ResponseEntity<HttpStatus> sendBalance(@RequestParam Double amount, @RequestParam int iduser) {
		if (walletService.findByidUser(iduser) != null) {
			Double newbalance = walletService.findByidUser(iduser).getBalance() - amount;
			walletService.updateBalance(newbalance, iduser);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/findwalletbyiduser")
	public ResponseEntity<Wallet> findByIdUser(@RequestParam int iduser) {
		if (walletService.findByidUser(iduser) != null) {
			return ResponseEntity.ok(walletService.findByidUser(iduser));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/getAllWaller")
	public ResponseEntity<List<Wallet>> getAll() {

			return ResponseEntity.ok(walletService.getAll());
		
	}
}
