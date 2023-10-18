package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Subscribe;

public interface SubscribeDAO extends JpaRepository<Subscribe, Integer>{
	@Query(value = "select * from subscribe where iduser=?1 and idchannel=?2",nativeQuery = true)
	Subscribe findByIduserAndIdchannel(int iduser,int idchannel);
	
	@Modifying
	@Transactional
	@Query(value = "insert into subscribe (iduser,idchannel) value(?1,?2)", nativeQuery = true)
	void addSub(int iduser, int idchannel);

	@Modifying
	@Transactional
	@Query(value = "delete from subscribe where iduser=?1 and idchannel=?2",nativeQuery = true)
	void deleteSub(int iduser,int idchannel);
	
}
