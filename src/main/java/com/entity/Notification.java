package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification", catalog = "memetube")
public class Notification {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Idnotification")
	private Integer idnotification;
	@ManyToOne
	@JoinColumn(name = "Iduser")
	private Users user;
	@ManyToOne
	@JoinColumn(name = "Idusersend")
	private Users usersend;
	@Column(name = "Title", length = 100)
	private String title;

	@Column(name = "Contents", length = 200)
	private String contents;

	@Column(name = "Datecreated")
	private Timestamp datecreate;

	@Column(name = "Checked")
	private Boolean checked;

	@Column(name = "Redirecturl", length = 100)
	private String redirecturl;

	@Column(name = "Status")
	private Boolean status;

}
