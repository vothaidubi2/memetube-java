package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Channel;
import com.service.ChannelService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ChannelRestController {
	@Autowired
	ChannelService channelService;
	
	@GetMapping("/findchannelbyiduser")
	public ResponseEntity<Channel> findByIdUser(@RequestParam int iduser){
		return ResponseEntity.ok(channelService.findByIdUser(iduser));
	}
}
