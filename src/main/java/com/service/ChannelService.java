package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ChannelDAO;
import com.entity.Category;
import com.entity.Channel;
import com.entity.Users;

@Service
public class ChannelService {
	@Autowired
	ChannelDAO dao;
	
	public Channel findByIdUser(int id) {
		return dao.findByIdUser(id);
	}
	public List<Channel> fillAll() {
		return dao.findAll();
	}
	public Channel UpdateStatus(int id,Boolean status) {
		Channel tempChannel =	dao.findById(id).get();
		tempChannel.setStatus(status);
		dao.save(tempChannel);
		return tempChannel;
	}
	public Channel updateChannel (Channel channel) {
		Channel temp=dao.findById(channel.getIdchannel()).get();
		temp.setChannelname(channel.getChannelname());
		dao.save(temp);
		return temp;
	}
}
