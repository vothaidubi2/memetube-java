package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomRatingDAO;
import com.dao.RatingDAO;
import com.entity.Rating;

@Service
public class RatingService {
	@Autowired
	RatingDAO ratingDAO;
	@Autowired
	CustomRatingDAO cusratingDAO;

	public Rating getRatingInfo(int idUser, int idVideo) {
		return ratingDAO.findByIduserAndIdvideo(idUser, idVideo);
	}

	public com.dto.CusRating countRating(int idVideo) {
		return cusratingDAO.countByIdvideo(idVideo);
	}

	public void addRate(int idUser, int idVideo, boolean rate) {
		ratingDAO.addRate(idUser, idVideo, rate);
	}

	public void updateRate(int idUser, int idVideo, boolean rate) {
		Rating rating = getRatingInfo(idUser,idVideo);
		rating.setRate(rate);
		ratingDAO.save(rating);
	}

	public void deleteRate(int idUser, int idVideo) {
		ratingDAO.deleteRate(idUser, idVideo);
	}
}
