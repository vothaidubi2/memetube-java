package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dto.UserDto;
import com.entity.Notification;

public interface NotificationDAO extends JpaRepository<Notification, Integer>{
	@Query(value="SELECT * FROM Notification where Iduser = ?1 and status = true order by datecreated DESC ",nativeQuery = true)
	List<Notification> findNotificationByIduser(int id);
	@Query(value="SELECT * FROM Notification where Redireacturl = ?1 and status = true order by datecreated DESC",nativeQuery = true)
	List<Notification> findNotifacionByUrl(String url);
}
