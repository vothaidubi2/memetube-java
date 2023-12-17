package com.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Notification;
import com.service.CreatePayment;
import com.service.NotificationService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class NotificationRestController {
	@Autowired
	NotificationService notificationService;
	@PostMapping("/addNotificationOnetoMany")
	public void postnotificationOnetoMany(@RequestBody Notification notification,@RequestParam int Idsend){
		notificationService.addNotificationOnetoMany(notification, Idsend);
	}
	@PostMapping("/addNotificationOnetoOne")
	public void addNotificationOnetoOne(@RequestBody Notification notification,@RequestParam int Idsend,@RequestParam int idreceive){
		notificationService.addNotificationOnetoOne(notification, Idsend,idreceive);
	}
	@PostMapping("/addNotificationRatingorSub")
	public void addNotificationByRatingorSub(@RequestBody Notification notification,@RequestParam int Idsend,@RequestParam int idreceive,@RequestParam int idvideo,@RequestParam int idchannel,@RequestParam String issub  ){
		notificationService.addNotificationByRatingorSub(notification, Idsend,idreceive,idvideo,idchannel,issub);
	}
	@GetMapping("/getNotificationByIduser")
	public ResponseEntity<List<Notification>> getnotification(@RequestParam int IdUser){
		
		return ResponseEntity.ok(notificationService.getnotification(IdUser));
	}
	@PutMapping("/updateChecked")
	public ResponseEntity<Notification> updateChecked (@RequestParam int idNotification){
		return ResponseEntity.ok(notificationService.updateChecked(idNotification));
	}
}
