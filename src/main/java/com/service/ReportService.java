package com.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CommentDAO;
import com.dao.ReportDAO;
import com.dao.UserDAO;
import com.dao.VideoDAO;
import com.dto.CusReport;
import com.entity.Report;

@Service
public class ReportService {
	@Autowired
	ReportDAO dao;
	@Autowired
	UserDAO userDAO;
	@Autowired
	CommentDAO commentDAO;
	@Autowired
	VideoDAO videoDAO;
	
	public void reportComment (int iduser,int idcomment,String content) {
		Report report = new Report();
		report.setUserReport(userDAO.findById(iduser).get());
		report.setComment(commentDAO.findById(idcomment).get());
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        Timestamp timestamp = Timestamp.valueOf(formattedDate);
        report.setDatecreate(timestamp);
        report.setStatus(false);
        report.setContent(content);
        dao.save(report);
	}
	public void reportVideo (int iduser,int idvideo,String content) {
		Report report = new Report();
		report.setUserReport(userDAO.findById(iduser).get());
		report.setVideo(videoDAO.findById(idvideo).get());
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = dateFormat.format(currentDate);
		Timestamp timestamp = Timestamp.valueOf(formattedDate);
		report.setDatecreate(timestamp);
		report.setStatus(false);
		report.setContent(content);
		dao.save(report);
	}
	
	public List<String> listDetailRpCmt() {
		return dao.getDetail();
	}
	public List<Report> getListReportCmt() {
		return dao.getListReportCmt();
	}
	public List<Report> getListReportVideo() {
		return dao.getListReportVideo();
	}

	public void deleteReport(int id) {
		dao.deleteById(id);
	}
	public void completeCommentReport(int id) {
		dao.deleteByCommentReport(id);
	}
	public void completeVideoReport(int id) {
		dao.deleteByVideoReport(id);
	}
}
