package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Category;
import com.entity.Channel;
import com.entity.Users;
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
	@GetMapping("/fillallchannel")
	public ResponseEntity<List<Channel>> fillAll(){
		return ResponseEntity.ok(channelService.fillAll());
	}
	@PutMapping("/updateChannelStatus")
	public ResponseEntity<?> updateRole(@RequestParam int id, @RequestParam Boolean status) {
		Channel tempChannel=channelService.UpdateStatus(id, status);
			return ResponseEntity.ok(tempChannel);

		}
	@PutMapping("/updateChannel")
	public ResponseEntity<?> updateChannel(@RequestBody Channel category) {
		Channel tempCategory=channelService.updateChannel(category);
			return ResponseEntity.ok(tempCategory);

		}


		
}
