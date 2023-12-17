package com.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ChannelDAO;
import com.dao.NotificationDAO;
import com.dao.RatingDAO;
import com.dao.SubscribeDAO;
import com.dao.UserDAO;
import com.entity.Notification;
import com.entity.Rating;
import com.entity.Subscribe;

@Service
public class NotificationService {
	@Autowired
	SubscribeDAO subDao;
	@Autowired
	NotificationDAO notificationDao;
	@Autowired
	ChannelDAO channelDao;
	@Autowired
	UserDAO userDao;
	@Autowired
	RatingDAO ratingDao;

	public void addNotificationOnetoMany(Notification notification, int Idsend) {
		int idchannel = channelDao.findByIdUser(Idsend).getIdchannel();
		List<Subscribe> listSub = subDao.findByIdchannel(idchannel);
		Date currentDate = new Date();
		Timestamp timestamp = new Timestamp(currentDate.getTime());
		for (int i = 0; i < listSub.size(); i++) {
			try {
				Notification tempnotification = new Notification();
				tempnotification.setChecked(notification.getChecked());
				tempnotification.setContents(notification.getContents());
				tempnotification.setRedirecturl(notification.getRedirecturl());
				tempnotification.setStatus(notification.getStatus());
				tempnotification.setTitle(notification.getTitle());
				tempnotification.setUser(listSub.get(i).getUsers());
				;
				tempnotification.setDatecreate(timestamp);
				;
				tempnotification.setUsersend(userDao.findById(Idsend).get());
				;
				notificationDao.save(tempnotification);
			} catch (Exception e) {
			}
		}

	}

	public void addNotificationOnetoOne(Notification notification, int Idsend, int receive) {
		Date currentDate = new Date();
		Timestamp timestamp = new Timestamp(currentDate.getTime());

		try {
			Notification tempnotification = new Notification();
			tempnotification.setChecked(notification.getChecked());
			tempnotification.setContents(notification.getContents());
			tempnotification.setRedirecturl(notification.getRedirecturl());
			tempnotification.setStatus(notification.getStatus());
			tempnotification.setTitle(notification.getTitle());
			tempnotification.setUser(userDao.findById(receive).get());
			;
			tempnotification.setDatecreate(timestamp);
			;
			tempnotification.setUsersend(userDao.findById(Idsend).get());
			;
			notificationDao.save(tempnotification);
		} catch (Exception e) {
		}

	}

	public List<Notification> getnotification(int iduser) {
		List<Notification> listNotification = notificationDao.findNotificationByIduser(iduser);
		return listNotification;
	}

	public Notification updateChecked(int idNotification) {

		Notification notification = notificationDao.findById(idNotification).get();
		notification.setChecked(true);
		return notificationDao.save(notification);

	}

	public boolean isWithin15Minutes(Timestamp timestamp) {
		Instant now = Instant.now();
		Instant timestampInstant = timestamp.toInstant();
		long minutesDifference = java.time.Duration.between(timestampInstant, now).toMinutes();
		return minutesDifference < 15;
	}

	public void addNotificationByRatingorSub(Notification notification, int Idsend, int receive, int idvideo, int idchannel, String issub) {
	    Date currentDate = new Date();
	    Timestamp timestamp = new Timestamp(currentDate.getTime());

	    boolean shouldAddNotification = false;

	    if (issub.equals("sub")) {

	        Subscribe subtemp = subDao.LastSub(idchannel);
	        shouldAddNotification = (subtemp == null || !isWithin15Minutes(subtemp.getDatecreate()));
	    } else if(issub.equals("video")) {
	        Rating ratingtemp = ratingDao.LastRating(idvideo);
	        shouldAddNotification = (ratingtemp == null || !isWithin15Minutes(ratingtemp.getDatecreate()));
	    }else if(issub.equals("comment")) {
	    	shouldAddNotification=true;
	    }else if(issub.equals("replycomment")) {
	    	shouldAddNotification=true;
	    }

	    if (shouldAddNotification) {
	        try {
	            Notification tempnotification = new Notification();
	            tempnotification.setChecked(notification.getChecked());
	            tempnotification.setContents(notification.getContents());
	            tempnotification.setRedirecturl(notification.getRedirecturl());
	            tempnotification.setStatus(notification.getStatus());
	            tempnotification.setTitle(notification.getTitle());
	            tempnotification.setUser(userDao.findById(receive).get());
	            tempnotification.setDatecreate(timestamp);
	            tempnotification.setUsersend(userDao.findById(Idsend).get());
	            notificationDao.save(tempnotification);
	        } catch (Exception e) {
	        }
	    }
	}

}
