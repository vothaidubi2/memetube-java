package com.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ChannelDAO;
import com.dao.SubscribeDAO;
import com.dao.UserDAO;
import com.entity.Subscribe;

@Service
public class SubscribeService {
	@Autowired
	SubscribeDAO dao;
	@Autowired
	UserDAO userDAO;
	@Autowired
	ChannelDAO channelDAO;

	public Subscribe getSubInfo(int idUser, int idChannel) {
		return dao.findByIduserAndIdchannel(idUser, idChannel);
	}
	
	public List<Subscribe> getAllByIdChannel(int idChannel) {
		return dao.findByIdchannel(idChannel);
	}

	public void addSub(int idUser,int idChannel) {
		 Date currentDate = new Date();
	        Timestamp timestamp = new Timestamp(currentDate.getTime());
		dao.addSub(idUser, idChannel,timestamp);
	}
	
	public void deleteSub(int idUser, int idChannel) {
		dao.deleteSub(idUser, idChannel);
	}
	public List<Object[]> getListSubCountByIduser(int iduser) {
		return dao.getListSubCountByIduser(iduser);
	}
}
