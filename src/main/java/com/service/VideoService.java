package com.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ChannelDAO;
import com.dao.VideoDAO;
import com.entity.Channel;
import com.entity.Video;

import jakarta.persistence.LockModeType;

@Service
public class VideoService {
	@Autowired
	VideoDAO videoDao;
	@Autowired
	ChannelDAO channelDAO;

	@Autowired
	private VideoQueue videoQueue;

	public List<Video> getAllVideo() {
		return videoDao.findAll();
	}

	public List<Integer> getAllByComment() {
		return videoDao.getAllByComment();
	}
	
	public List<Video> getAllVideoByIdcategory(int id) {
		return videoDao.findByIdcategory(id);
	}
	public List<Video> getAllVideoByIdUser(int iduser) {
		Channel channel = channelDAO.findByIdUser(iduser);
		return videoDao.findByIdChannel(channel.getIdchannel());
	}

	public Video getOneVideo(int id) {
		try {
			return videoDao.findById(id).get();			
		} catch (Exception e) {
			return new Video();
		}
	}
	
	public Video postVideo(Video video) {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        Timestamp timestamp = Timestamp.valueOf(formattedDate);
        video.setDatecreated(timestamp);
		return videoDao.save(video);
	}
	
	public Video updateVideo(Video video) {
		return videoDao.save(video);
	}
	
	public List<Video> searchVideo(String input) {
		return videoDao.findByTitleLike('%'+input+'%');
	}
	public void deleteVideo(int id) {
		videoDao.deleteById(id);
	}

	@Transactional
	public boolean enqueueSetCount(int id) {
		try {
			videoQueue.enqueue(new VideoRequest(id));
			return true;
		} catch (Exception e) {
			// Xử lý nếu có lỗi khi thêm vào hàng đợi (ví dụ: hàng đợi đầy)
			return false;
		}
	}

	@Transactional
	@Scheduled(fixedRate = 60000)
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public boolean processQueue() {
		System.out.println("vao day");
		try {
			VideoRequest request = VideoQueue.dequeue();
			int videoId = request.getVideoId();
			videoDao.incrementViewCount(videoId);
			return true;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return false;
		}
	}
}
