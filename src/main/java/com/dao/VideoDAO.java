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

	@Query(value = "SELECT * FROM memetube.video where Idcategory=?1 and memetube.video.status = 1", nativeQuery = true)
	List<Video> findByIdcategory(int id);

	@Query(value = "SELECT * FROM memetube.video where idchannel=?1 and status = 1", nativeQuery = true)
	List<Video> findByIdChannel(int id);

	@Query(value = "SELECT * FROM memetube.video where status = 1", nativeQuery = true)
	List<Video> getAllVideo();

	@Query(value = "select * from video where status = 1 order by viewcount desc", nativeQuery = true)
	List<Video> findTop1Video();

	@Query(value = "SELECT * FROM memetube.video where status = 1 limit 10", nativeQuery = true)
	List<Video> findTop10Video();

	@Query(value = "SELECT sum(viewcount) FROM memetube.video ", nativeQuery = true)
	int sumView();

	@Query(value = "SELECT video.idvideo FROM video join comment on video.idvideo = comment.idvideo group by idvideo;", nativeQuery = true)
	List<Integer> getAllByComment();

	@Query(value = "SELECT video.idvideo FROM video join comment on video.idvideo = comment.idvideo where video.idchannel=?1 group by idvideo", nativeQuery = true)
	List<Integer> getAllByCommentChannel(int idchannel);

	@Query(value = "select * from video where title like ?1 and status = 1", nativeQuery = true)
	List<Video> findByTitle(String title);
	
	@Query(value = "select * from video where title like ?1", nativeQuery = true)
	List<Video> showListSearch(String title);
}
