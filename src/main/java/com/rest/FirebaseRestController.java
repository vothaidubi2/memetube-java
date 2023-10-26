package com.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.FirebaseService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FirebaseRestController {
	@Autowired
	FirebaseService imageService;
	
	@PostMapping("/uploadimage")
	public ResponseEntity<String> uploadImages(@RequestParam("files") MultipartFile files) {
			return imageService.uploadImages(files);	
	}

	@PostMapping("/uploadvideo")
	public ResponseEntity<String> uploadVideo(@RequestParam("files") MultipartFile files) {
		return imageService.uploadVideo(files);
	}
}
