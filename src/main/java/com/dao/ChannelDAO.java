package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Channel;

public interface ChannelDAO extends JpaRepository<Channel, Integer>{
	@Query(value = "select * from channel where iduser = ?1",nativeQuery = true)
	Channel findByIdUser(int id);
}
