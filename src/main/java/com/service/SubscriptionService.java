package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SubscribeDAO;
import com.dao.VideoDAO;
import com.entity.Subscribe;
import com.entity.Video;

@Service
public class SubscriptionService {
	@Autowired
	SubscribeDAO subDao;
	@Autowired
	VideoDAO videoDao;
	
	public List<Video> getSubscription( int iduser){
		List<Subscribe>listSub= subDao.findSubByUser(iduser);
		List<Video> listVideo=new ArrayList<Video>();
		if(listSub!=null && listSub.size()!=0) {
			
			for(int i=0;i<listSub.size();i++) {
				listVideo.addAll(videoDao.findByIdChannel(listSub.get(i).getChannel().getIdchannel()));
			}
	
			
		}
		return listVideo;
		
	}
}
