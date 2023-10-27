package com.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Comment;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
	@Query(value = "SELECT * FROM memetube.comment where idvideo=?1 and idbasecmt is NULL", nativeQuery = true)
	List<Comment> findAllBaseCmt(int idVideo);

	@Query(value = "SELECT * FROM memetube.comment where idvideo=?1 and idbasecmt =?2", nativeQuery = true)
	List<Comment> findAllReplyCmt(int idVideo, int idBasecmt);

	@Modifying
	@Transactional
	@Query(value = "insert into comment (idvideo,iduser,contents,datecreated,idbasecmt) value (?1,?2,?3,?4,null)",nativeQuery = true)
	void postComment(int idVideo, int idUser, String contents, Timestamp date);
}
