package com.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CommentDAO;
import com.entity.Comment;
import com.utils.ChangeDate;

@Service
public class CommentService {
	@Autowired
	CommentDAO dao;
	@Autowired
	ChangeDate changeDate;

	public List<Comment> getAllBaseCmt(int id) {
		return dao.findAllBaseCmt(id);
	}

	public List<Comment> getAllReplyCmt(int idVideo, int idBaseCmt) {
		return dao.findAllReplyCmt(idVideo, idBaseCmt);
	}
	public Object getTotalCommentForDateRange(String dateStart, String dateEnd) {
		try {
			long start = changeDate.changeDate(dateStart);
			System.out.println("dau: "+start);
			long end = changeDate.changeDate(dateEnd);
			System.out.println("cuoi: "+end);
			List<String> result = new ArrayList<>();
			if (dao.getTotalCommentForDateRange(start, end) != null) {
				result = dao.getTotalCommentForDateRange(start, end);
			}
			Map<String, List<String>> data = new HashMap<>();
			data.put("data", result);
			return data;
		} catch (Exception e) {
			return null;
		}
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
	
	public void postReplyComment(int idVideo, int idUser, String contents,int idbasecmt) {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateFormat.format(currentDate);
		Timestamp timestamp = Timestamp.valueOf(formattedDate);
		dao.postReplyComment(idVideo, idUser, contents, timestamp,idbasecmt);
	}
}
