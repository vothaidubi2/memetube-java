package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Video;

import jakarta.persistence.LockModeType;

public interface VideoDAO extends JpaRepository<Video, Integer> {
	@Modifying
	@Transactional
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query(value = "UPDATE Video v SET v.viewcount = v.viewcount + 1 WHERE v.idvideo = ?1")
	int incrementViewCount(int id);

	List<Video> findByTitleLike(String title);
}
