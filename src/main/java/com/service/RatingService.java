package com.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.html.ListView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomRatingDAO;
import com.dao.RatingDAO;
import com.dao.VideoDAO;
import com.entity.Rating;
import com.entity.Video;

@Service
public class RatingService {
	@Autowired
	RatingDAO ratingDAO;
	@Autowired
	CustomRatingDAO cusratingDAO;
	@Autowired
	VideoDAO videoDao;

	public Rating getRatingInfo(int idUser, int idVideo) {
		return ratingDAO.findByIduserAndIdvideo(idUser, idVideo);
	}

	public com.dto.CusRating countRating(int idVideo) {
		return cusratingDAO.countByIdvideo(idVideo);
	}

	public void addRate(int idUser, int idVideo, boolean rate) {
		 Date currentDate = new Date();
	        Timestamp timestamp = new Timestamp(currentDate.getTime());
		ratingDAO.addRate(idUser, idVideo, rate,timestamp);
	}

	public void updateRate(int idUser, int idVideo, boolean rate) {
		Rating rating = getRatingInfo(idUser,idVideo);
		rating.setRate(rate);
		ratingDAO.save(rating);
	}

	public void deleteRate(int idUser, int idVideo) {
		ratingDAO.deleteRate(idUser, idVideo);
	}
	public List<Video> getVideoLike(int iduser){
	List<Rating> listRating=	ratingDAO.listVideoRating(iduser);
	List<Video>listVideo=new ArrayList<Video>();
		if(listRating!=null && listRating.size()!=0) {

			for(int i=0;i<listRating.size();i++) {
				listVideo.add(videoDao.findByIdVideo(listRating.get(i).getVideo().getIdvideo()));
			}
		}
		return listVideo;
	}
}
