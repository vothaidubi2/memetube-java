package com.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Video;
import com.service.VideoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class VideoRestController {
	@Autowired
	VideoService videoService;

	@GetMapping("/listvideo")
	public ResponseEntity<Map<String, Object>> getAll() {
		Map<String, Object> response = new HashMap<>();
		response.put("total", videoService.getAllVideo().size());
		response.put("data", videoService.getAllVideo());

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/searchvideo")
	public ResponseEntity<Map<String, Object>> searchVideo(@RequestParam String search) {
		Map<String, Object> response = new HashMap<>();
		response.put("total", videoService.searchVideo(search).size());
		response.put("data", videoService.searchVideo(search));

		return ResponseEntity.ok(response);
	}

	@GetMapping("/getonevideo")
	public ResponseEntity<Video> getOne(@RequestParam int id) {
		if (videoService.getOneVideo(id) != null) {
			return ResponseEntity.ok(videoService.getOneVideo(id));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/setcountvideo")
	public ResponseEntity<HttpStatus> setCount(@RequestParam int id) {
		if (videoService.getOneVideo(id) != null) {
			videoService.enqueueSetCount(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
