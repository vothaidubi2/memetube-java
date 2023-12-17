package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class FirebaseService {
	public ResponseEntity<String> uploadImages(@RequestParam("files") MultipartFile files) {
		try {
			InputStream serviceAccount = getClass().getResourceAsStream("/videoAccountKey.json");
			GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
			Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

			List<String> imageUrls = new ArrayList<>();

			// Upload each image to Firebase Storage and get their URLs

			LocalDateTime date = LocalDateTime.now();
			int seconds = date.toLocalTime().toSecondOfDay();
			String imageName = seconds + "-" + files.getOriginalFilename();
			String bucketName = "memetube-2.appspot.com";
			BlobId blobId = BlobId.of(bucketName, imageName);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(files.getContentType()).build();
			Blob blob = storage.create(blobInfo, files.getBytes());
			String imageUrl = storage.get(bucketName, imageName).signUrl(365, TimeUnit.DAYS).toString();
			imageUrls.add(imageUrl);
			return ResponseEntity.status(HttpStatus.CREATED).body(imageUrl);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	public ResponseEntity<String> uploadVideo(@RequestParam("files") MultipartFile files) {
		try {
			// Initialize Firebase Storage
			InputStream serviceAccount = getClass().getResourceAsStream("/videoAccountKey.json");
			GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
			Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
			List<String> imageUrls = new ArrayList<>();
			LocalDateTime date = LocalDateTime.now();
			int seconds = date.toLocalTime().toSecondOfDay();
			String imageName = seconds + "-" + files.getOriginalFilename();
			String bucketName = "memetube-2.appspot.com";
			BlobId blobId = BlobId.of(bucketName, imageName);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(files.getContentType()).build();
			Blob blob = storage.create(blobInfo, files.getBytes());
			String imageUrl = storage.get(bucketName, imageName).signUrl(365, TimeUnit.DAYS).toString();
			imageUrls.add(imageUrl);
			return ResponseEntity.status(HttpStatus.CREATED).body(imageUrl);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
