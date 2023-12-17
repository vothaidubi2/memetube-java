package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Subscribe;
import com.service.SubscribeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SubscribeRestController {
	@Autowired
	SubscribeService service;

	@GetMapping("/getsubinfo")
	public ResponseEntity<Subscribe> getSubInfo(@RequestParam int iduser, @RequestParam int idchannel) {
		if (service.getSubInfo(iduser, idchannel) != null) {
			return ResponseEntity.ok(service.getSubInfo(iduser, idchannel));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/getallsubscribe")
	public ResponseEntity<List<Subscribe>> gettByIdChannel(@RequestParam int idchannel) {
		if (service.getAllByIdChannel(idchannel) != null) {
			return ResponseEntity.ok(service.getAllByIdChannel(idchannel));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/addsub")
	public ResponseEntity<HttpStatus> addSub(@RequestParam int iduser, @RequestParam int idchannel) {
		if (service.getSubInfo(iduser, idchannel) != null) {
			return ResponseEntity.badRequest().build();
		} else {
			service.addSub(iduser, idchannel);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

	@DeleteMapping("/deletesub")
	public ResponseEntity<HttpStatus> deleteSub(@RequestParam int iduser, @RequestParam int idchannel) {
		if (service.getSubInfo(iduser, idchannel) != null) {
			service.deleteSub(iduser, idchannel);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/getCountSubById")
	public ResponseEntity<List<Object[]>> getCountSubById(@RequestParam int iduser) {
		return ResponseEntity.ok(service.getListSubCountByIduser(iduser));
	}
	
}
