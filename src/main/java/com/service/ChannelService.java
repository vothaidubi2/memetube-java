package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ChannelDAO;
import com.entity.Channel;

@Service
public class ChannelService {
	@Autowired
	ChannelDAO dao;
	
	public Channel findByIdUser(int id) {
		return dao.findByIdUser(id);
	}
}
