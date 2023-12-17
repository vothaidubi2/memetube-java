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

	@Query(value = "SELECT * FROM memetube.comment where idbasecmt =?1", nativeQuery = true)
	List<Comment> findAllByBaseCmt(int idBasecmt);

	@Query(value = "SELECT CAST( comment.Datecreated AS DATE) as 'date',count(comment.Idcomment) as 'count' FROM memetube.comment join video on video.idvideo=comment.idvideo where video.idchannel=1 and CAST( comment.Datecreated AS DATE) between DATE_SUB(cast(NOW() as date), INTERVAL ?1 DAY) and DATE_SUB(cast(NOW() as date), INTERVAL ?2 DAY) group by CAST( comment.Datecreated AS DATE) order by date desc", nativeQuery = true)
	List<String> getTotalCommentForDateRange(long dateStart, long dateEnd);

	@Modifying
	@Transactional
	@Query(value = "insert into comment (idvideo,iduser,contents,datecreated,idbasecmt) value (?1,?2,?3,?4,null)", nativeQuery = true)
	void postComment(int idVideo, int idUser, String contents, Timestamp date);

	@Modifying
	@Transactional
	@Query(value = "insert into comment (idvideo,iduser,contents,datecreated,idbasecmt) value (?1,?2,?3,?4,?5)", nativeQuery = true)
	void postReplyComment(int idVideo, int idUser, String contents, Timestamp date, int idbasecmt);

	@Modifying
	@Transactional
	@Query(value = "delete from comment where idcomment=?1 ", nativeQuery = true)
	void deleteBaseComment(int idcomment);

	@Modifying
	@Transactional
	@Query(value = "delete from comment where idbasecmt=?1 and idcomment>0", nativeQuery = true)
	void deleteReplyComment(int idbasecmt);
}
