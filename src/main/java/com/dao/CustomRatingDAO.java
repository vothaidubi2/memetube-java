package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dto.CusRating;

public interface CustomRatingDAO extends JpaRepository<CusRating, Integer>{
	@Query(value = "select row_number()over(order BY count(rate)) as 'id',(select count(rate) from rating where idvideo=:id and rate=false) as 'dislike',(select count(rate) from rating where idvideo=:id and rate=true) as 'like' from rating where idvideo=:id\r\n"
			+ "\r\n"
			+ "", nativeQuery = true)
	com.dto.CusRating countByIdvideo(@Param("id")int idvideo);
}
