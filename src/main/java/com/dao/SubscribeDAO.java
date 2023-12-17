package com.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.entity.Rating;
import com.entity.Subscribe;

public interface SubscribeDAO extends JpaRepository<Subscribe, Integer>{
	@Query(value = "select * from subscribe where iduser=?1 and idchannel=?2",nativeQuery = true)
	Subscribe findByIduserAndIdchannel(int iduser,int idchannel);
	@Query(value = "Select * from  subscribe where idsub=?1 order by datecreated DESC limit 1 OFFSET 1", nativeQuery = true)
	Subscribe LastSub(int idsub);
	@Query(value = "select * from subscribe where idchannel=?1",nativeQuery = true)
	List<Subscribe> findByIdchannel(int idchannel);
	@Query(value = "select * from subscribe where iduser =?1",nativeQuery = true)
	List<Subscribe> findSubByUser(int iduser);
	@Modifying
	@Transactional
	@Query(value = "insert into subscribe (iduser,idchannel,datecreated) value(?1,?2,?3)", nativeQuery = true)
	void addSub(int iduser, int idchannel,Timestamp datecreated);

	@Modifying
	@Transactional
	@Query(value = "delete from subscribe where iduser=?1 and idchannel=?2",nativeQuery = true)
	void deleteSub(int iduser,int idchannel);
	  @Query(
		        value = "SELECT c.idchannel as idchannel, c.channelname as channelname, u.avatar as avatar , COUNT(s.iduser) as subscriberCount " +
		            "FROM channel c " +
		            "LEFT JOIN subscribe s ON c.idchannel = s.idchannel " +
		            "LEFT JOIN users u ON s.iduser = u.iduser " +
		            "WHERE c.idchannel IN (SELECT c2.idchannel FROM users u2 " +
		            "JOIN subscribe s2 ON u2.iduser = s2.iduser " +
		            "JOIN channel c2 ON s2.idchannel = c2.idchannel WHERE u2.iduser = :iduser) " +
		            "GROUP BY c.idchannel",
		        nativeQuery = true
		    )
		    List<Object[]> getListSubCountByIduser(@Param("iduser") int iduser);



	
}
