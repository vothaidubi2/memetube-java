package com.entity;
// Generated Oct 5, 2023, 8:54:31 AM by Hibernate Tools 4.3.6.Final

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment generated by hbm2java
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment", catalog = "memetube")
public class Comment{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Idcomment", unique = true, nullable = false)
	private Integer idcomment;
	@ManyToOne
	@JoinColumn(name = "Iduser")
	private Users users;
	@ManyToOne
	@JoinColumn(name = "Idvideo")
	private Video video;
	@Column(name = "Contents")
	private String contents;
	@Temporal(TemporalType.DATE)
	@Column(name = "Datecreated", length = 10)
	private Date datecreated;

}
