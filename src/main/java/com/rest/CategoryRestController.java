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
import com.entity.Users;
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
	@PutMapping("/updateCategoryStatus")
	public ResponseEntity<?> updateStatus(@RequestParam int id, @RequestParam Boolean status) {
		Category tempCategory=cateService.UpdateStatus(id, status);
			return ResponseEntity.ok(tempCategory);

		}
	@PutMapping("/updateCategory")
	public ResponseEntity<?> updateCategory(@RequestBody Category category) {
		Category tempCategory=cateService.updateCategory(category);
			return ResponseEntity.ok(tempCategory);

		}
	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@RequestParam String category) {
		Category tempCategory=cateService.addCategory(category);
			return ResponseEntity.ok(tempCategory);

		}
}
