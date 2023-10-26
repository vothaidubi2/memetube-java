package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Comment;
import com.service.CommentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CommentRestController {
	@Autowired
	CommentService cmtService;
	
	@GetMapping("/getallbasecmt")
	public ResponseEntity<List<Comment>> getAllBaseCmt(@RequestParam int idvideo){
		return ResponseEntity.ok(cmtService.getAllBaseCmt(idvideo));
	}
	@PostMapping("/postcomment")
	public ResponseEntity<HttpStatus> postComment(@RequestParam int idvideo,@RequestParam int iduser,@RequestParam String contents,@RequestParam String date){
		cmtService.postComment(idvideo,iduser,contents,date);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
