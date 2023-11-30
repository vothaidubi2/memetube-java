package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	@GetMapping("/getallreplycmt")
	public ResponseEntity<List<Comment>> getAllReplyCmt(@RequestParam int idvideo,@RequestParam int idbasecmt){
		return ResponseEntity.ok(cmtService.getAllReplyCmt(idvideo,idbasecmt));
	}
	@GetMapping("/gettotalcommentfordaterange")
	public ResponseEntity<Object> getTotalCommentForDateRange(@RequestParam String datestart,@RequestParam String dateend){
		return ResponseEntity.ok(cmtService.getTotalCommentForDateRange(datestart, dateend));
	}
	@PostMapping("/postcomment")
	public ResponseEntity<HttpStatus> postComment(@RequestParam int idvideo,@RequestParam int iduser,@RequestParam String contents){
		cmtService.postComment(idvideo,iduser,contents);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PostMapping("/postreplycomment")
	public ResponseEntity<HttpStatus> postReplyComment(@RequestParam int idvideo,@RequestParam int iduser,@RequestParam String contents,@RequestParam int idbasecmt){
		cmtService.postReplyComment(idvideo,iduser,contents,idbasecmt);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@DeleteMapping("/deletecomment")
	public ResponseEntity<HttpStatus> deleteComment(@RequestParam int idcomment,@RequestParam int idbasecmt){
		cmtService.deleteComment(idcomment, idbasecmt);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
