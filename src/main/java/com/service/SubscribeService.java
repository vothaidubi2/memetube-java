package com.service;

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

	public void addSub(int idUser,int idChannel) {
		dao.addSub(idUser, idChannel);
	}
	
	public void deleteSub(int idUser, int idChannel) {
		dao.deleteSub(idUser, idChannel);
	}
}
