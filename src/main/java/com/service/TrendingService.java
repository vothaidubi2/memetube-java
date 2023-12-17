package com.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TrendingDAO;
import com.dao.VideoDAO;
import com.entity.Trending;

@Service
public class TrendingService {
	@Autowired 
	TrendingDAO trendingDao;
@Autowired
VideoDAO videoDao;
	public List<Trending> getTrending(){
		return trendingDao.getListVideo();
	}
	public Trending postVideoTrending(int idVideo) {
		 Date currentDate = new Date();
	        Timestamp timestamp = new Timestamp(currentDate.getTime());
	        Trending trendingtemp=new Trending();
	        trendingtemp.setDatecreated(timestamp);;
	        trendingtemp.setVideo(videoDao.findById(idVideo).get());
	        trendingDao.save(trendingtemp);
	        return trendingtemp;
	}
}
