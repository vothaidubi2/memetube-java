package com.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Trending;
import com.service.TrendingService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TrendingRestController {
	@Autowired
	TrendingService trendingService;
	@GetMapping("/trending")
	public ResponseEntity<Map<String, Object>> getTrending() {
		Map<String, Object> response = new HashMap<>();
		response.put("total", trendingService.getTrending().size());
		response.put("data", trendingService.getTrending());
		return ResponseEntity.ok(response);
	}
	@PostMapping("/postVideoTrending")
	public ResponseEntity<Trending> postVideoTrending(@RequestParam int idVideo) {
		
		return ResponseEntity.ok(trendingService.postVideoTrending(idVideo));
	}
 
}
