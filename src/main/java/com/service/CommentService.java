package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CommentDAO;
import com.entity.Comment;

@Service
public class CommentService {
	@Autowired
	CommentDAO dao;
	
	public List<Comment> getAllBaseCmt(int id){
		return dao.findAllBaseCmt(id);
	}
	public List<Comment> getAllReplyCmt(int idVideo,int idBaseCmt){
		return dao.findAllReplyCmt(idVideo,idBaseCmt);
	}
	public void postComment(int idVideo,int idUser,String contents,String date) {
		dao.postComment(idVideo, idUser, contents, date);
	}
}
