package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Category;
import com.service.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CategoryRestController {
	@Autowired
	CategoryService cateService;

	@GetMapping("/getallcate")
	public ResponseEntity<List<Category>> getAll() {
		if (cateService.getAll() != null) {
			return ResponseEntity.ok(cateService.getAll());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getcatebyid")
	public ResponseEntity<Category> getAll(@RequestParam int id) {
			return ResponseEntity.ok(cateService.getById(id));
	}
}
