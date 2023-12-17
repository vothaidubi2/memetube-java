package com.rest;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Rating;
import com.entity.Video;
import com.service.RatingService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RatingRestController {
	@Autowired
	RatingService ratingService;

	@GetMapping("/getratinginfo")
	public ResponseEntity<Rating> getRatingInfo(@RequestParam int iduser, @RequestParam int idvideo) {
		if (ratingService.getRatingInfo(iduser, idvideo) != null) {
			return ResponseEntity.ok(ratingService.getRatingInfo(iduser, idvideo));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/countrating")
	public ResponseEntity<com.dto.CusRating> countRating(@RequestParam int idvideo) {
		return ResponseEntity.ok(ratingService.countRating(idvideo));
	}
	
	@PostMapping("/addrate")
	public ResponseEntity<HttpStatus> addRate(@RequestParam int iduser, @RequestParam int idvideo,@RequestParam boolean rate) {
		if (ratingService.getRatingInfo(iduser, idvideo) == null) {
			ratingService.addRate(iduser, idvideo,rate);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	@PutMapping("/updaterate")
	public ResponseEntity<HttpStatus> updateRate(@RequestParam int iduser, @RequestParam int idvideo,@RequestParam boolean rate) {
		if (ratingService.getRatingInfo(iduser, idvideo) != null) {
			ratingService.updateRate(iduser, idvideo,rate);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	@DeleteMapping("/deleterate")
	public ResponseEntity<HttpStatus> deleteSub(@RequestParam int iduser, @RequestParam int idvideo) {
		if (ratingService.getRatingInfo(iduser, idvideo) != null) {
			ratingService.deleteRate(iduser, idvideo);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/getLikedvideo")
	public ResponseEntity<List<Video>> getLikeVideo(@RequestParam int iduser) {

			return ResponseEntity.ok(ratingService.getVideoLike(iduser));
	}
	
}
