package com.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Rating;

public interface RatingDAO extends JpaRepository<Rating, Integer> {
	@Query(value = "select * from rating where iduser=?1 and idvideo=?2", nativeQuery = true)
	Rating findByIduserAndIdvideo(int iduser, int idvideo);
	@Query(value = "Select * from  rating where idvideo=?1 order by datecreated DESC limit 1 OFFSET 1", nativeQuery = true)
	Rating LastRating(int idvideo);
	@Query(value = "Select * from  rating where iduser=?1 and rate=true", nativeQuery = true)
	List<Rating> listVideoRating(int idvideo);

	@Modifying
	@Transactional
	@Query(value = "insert into rating (idUser,idVideo,rate,datecreated) value (?1,?2,?3,?4)", nativeQuery = true)
	void addRate(int idUser, int idVideo, boolean rate,Timestamp datecreated);

	@Modifying
	@Transactional
	@Query(value = "delete from rating where iduser=?1 and idvideo=?2",nativeQuery = true)
	void deleteRate(int iduser,int idvideo);
}
