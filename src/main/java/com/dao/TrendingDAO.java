package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Trending;

public interface TrendingDAO extends JpaRepository<Trending, Integer>{
	@Query(value = "SELECT Idtrending ,Idvideo,Datecreated , COUNT(Idvideo) FROM trending WHERE DATE(Datecreated) = CURDATE() GROUP BY Idvideo ORDER BY  COUNT(Idvideo) DESC; ",nativeQuery = true)
	List<Trending>getListVideo();

}
