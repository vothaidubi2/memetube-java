package com.entity;
// Generated Oct 5, 2023, 8:54:31 AM by Hibernate Tools 4.3.6.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Users generated by hbm2java
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", catalog = "memetube")
public class Users {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Iduser", unique = true, nullable = false)
	private Integer iduser;
	@Column(name = "Username", length = 50)
	private String username;
	@Column(name = "Password")
	private String password;
	@Temporal(TemporalType.DATE)
	@Column(name = "Datecreated", length = 10)
	private Date datecreated;
	@Column(name = "Email", length = 100)
	private String email;
	@Column(name = "Status")
	private Boolean status;
	@Column(name = "Role")
	private Boolean role;
	@Column(name = "Avatar")
	private String avatar;
	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<Channel> channels = new ArrayList<>();
	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<Rating> ratings = new ArrayList<>();
	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<Subscribe> subcribes = new ArrayList<>();
	@OneToMany(mappedBy = "users")
	@JsonIgnore
	private List<Comment> comments = new ArrayList<>();
}
