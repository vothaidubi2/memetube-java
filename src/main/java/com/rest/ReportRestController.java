package com.rest;

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

import com.dao.CommentDAO;
import com.dao.ReportDAO;
import com.dao.UserDAO;
import com.dao.VideoDAO;
import com.dto.CusReport;
import com.entity.Report;
import com.entity.Video;
import com.service.ReportService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ReportRestController {
	@Autowired
	ReportDAO dao;
	@Autowired
	ReportService reportService;
	@Autowired
	UserDAO userDAO;
	@Autowired
	CommentDAO commentDAO;
	@Autowired
	VideoDAO videoDAO;

	@GetMapping("/getdetailreportcomment")
	public ResponseEntity<List<String>> getDetailReportComment() {
		return ResponseEntity.ok(reportService.listDetailRpCmt());
	}

	@GetMapping("/getlistreportcmt")
	public ResponseEntity<List<Report>> getListReportCmt() {
		return ResponseEntity.ok(reportService.getListReportCmt());
	}

	@GetMapping("/getlistreportvideo")
	public ResponseEntity<List<Report>> getListReportVideo() {
		return ResponseEntity.ok(reportService.getListReportVideo());
	}

	@PostMapping("/reportcomment")
	public ResponseEntity<HttpStatus> reportComment(@RequestParam int iduser, @RequestParam int idcomment,
			@RequestParam String content) {
		if (userDAO.findById(iduser).isEmpty() || commentDAO.findById(idcomment).isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			reportService.reportComment(iduser, idcomment, content);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}

	@PostMapping("/reportvideo")
	public ResponseEntity<HttpStatus> reportVideo(@RequestParam int iduser, @RequestParam int idvideo,
			@RequestParam String content) {
		if (userDAO.findById(iduser).isEmpty() || videoDAO.findById(idvideo).isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			reportService.reportVideo(iduser, idvideo, content);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}

	@DeleteMapping("/deletereport")
	public ResponseEntity<HttpStatus> deleteReport(@RequestParam int id) {
		if (dao.findById(id).isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			reportService.deleteReport(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}
	
	@PutMapping("/completevideoreport")
	public ResponseEntity<HttpStatus> deleteVideoReport(@RequestParam int idvideo){
		if (videoDAO.findById(idvideo).isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			Video video =  videoDAO.findById(idvideo).get();
			video.setStatus(false);
			videoDAO.save(video);
			reportService.completeVideoReport(idvideo);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}

	@DeleteMapping("/completecommentreport")
	public ResponseEntity<HttpStatus> deleteCommentReport(@RequestParam int idcomment) {
		if (commentDAO.findById(idcomment).isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			if (commentDAO.findById(idcomment).get().getIdbasecmt() != null) {
				reportService.completeCommentReport(idcomment);
				commentDAO.deleteById(idcomment);
			}else {
				reportService.completeCommentReport(idcomment);
				commentDAO.deleteReplyComment(idcomment);
				commentDAO.deleteById(idcomment);
			}
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}
}
