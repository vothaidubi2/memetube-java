package com.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Rating;

public interface RatingDAO extends JpaRepository<Rating, Integer> {
	@Query(value = "select * from rating where iduser=?1 and idvideo=?2", nativeQuery = true)
	Rating findByIduserAndIdvideo(int iduser, int idvideo);

	@Modifying
	@Transactional
	@Query(value = "insert into rating (idUser,idVideo,rate) value (?1,?2,?3)", nativeQuery = true)
	void addRate(int idUser, int idVideo, boolean rate);

	@Modifying
	@Transactional
	@Query(value = "delete from rating where iduser=?1 and idvideo=?2",nativeQuery = true)
	void deleteRate(int iduser,int idvideo);
}
