package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Report;

public interface ReportDAO extends JpaRepository<Report, Integer> {
	@Query(value = "SELECT content,count(Idreport) as quantity FROM memetube.report where status=0 group by content;", nativeQuery = true)
	List<String> getDetail();

	@Query(value = "SELECT * FROM memetube.report where idcomment is not null and status =0 ; ", nativeQuery = true)
	List<Report> getListReportCmt();

	@Query(value = "SELECT * FROM memetube.report where idvideo is not null and status =0 ; ", nativeQuery = true)
	List<Report> getListReportVideo();
	
	@Modifying
	@Transactional
	@Query(value = "delete from report where idcomment=?1 and status=0",nativeQuery = true)
	void deleteByCommentReport(int idcomment);

	@Modifying
	@Transactional
	@Query(value = "delete from report where idvideo=?1 and status=0", nativeQuery = true)
	void deleteByVideoReport(int idvideo);
}
