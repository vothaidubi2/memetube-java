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

	@Query(value = "SELECT * FROM memetube.video where Idcategory=?1",nativeQuery = true)
	List<Video> findByIdcategory(int id);

	@Query(value = "SELECT * FROM memetube.video where idchannel=?1", nativeQuery = true)
	List<Video> findByIdChannel(int id);
	@Query(value = "select * from video order by viewcount desc", nativeQuery = true)
	List<Video> findTop1Video();

	@Query(value = "SELECT sum(viewcount) FROM memetube.video;", nativeQuery = true)
	int sumView();

	@Query(value = "SELECT video.idvideo FROM video join comment on video.idvideo = comment.idvideo group by idvideo;", nativeQuery = true)
	List<Integer> getAllByComment();
	
	List<Video> findByTitleLike(String title);
}
