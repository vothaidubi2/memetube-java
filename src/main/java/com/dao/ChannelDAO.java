package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Channel;

public interface ChannelDAO extends JpaRepository<Channel, Integer>{

}
