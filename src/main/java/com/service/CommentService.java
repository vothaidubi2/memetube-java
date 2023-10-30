package com.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CommentDAO;
import com.entity.Comment;

@Service
public class CommentService {
	@Autowired
	CommentDAO dao;

	public List<Comment> getAllBaseCmt(int id) {
		return dao.findAllBaseCmt(id);
	}

	public List<Comment> getAllReplyCmt(int idVideo, int idBaseCmt) {
		return dao.findAllReplyCmt(idVideo, idBaseCmt);
	}

	public void deleteComment(int idcomment, int idbasecmt) {
		dao.deleteBaseComment(idcomment);
		dao.deleteReplyComment(idbasecmt);
	}

	public void postComment(int idVideo, int idUser, String contents) {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateFormat.format(currentDate);
		Timestamp timestamp = Timestamp.valueOf(formattedDate);
		dao.postComment(idVideo, idUser, contents, timestamp);
	}
}
