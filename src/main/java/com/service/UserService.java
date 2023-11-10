package com.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.UserDAO;
import com.dto.UserDto;
import com.entity.Users;
import com.impl.CusUserDetailsImpl;

@Service
@Primary
public class UserService implements UserDetailsService {
	@Bean
	public BCryptPasswordEncoder psE() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private UserDAO dao;
	
	public Users getByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// load user form database
		Users user = dao.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		} else {
			return new CusUserDetailsImpl(user);
		}

	}

	public void addUser(Users user) {
		if (user.getStatus() == true) {
			if (user.getGoogle() == true) {
				if (dao.findByEmailAndGoogleTrue(user.getEmail()) == null) {
					Date currentDate = new Date();
					user.setDatecreated(currentDate);
					user.setPassword(psE().encode(user.getPassword()));
					dao.save(user);
				}
			} else {
				if (dao.findByEmailAndGoogleFalse(user.getEmail()) == null) {
					Date currentDate = new Date();
					user.setDatecreated(currentDate);
					user.setPassword(psE().encode(user.getPassword()));
					dao.save(user);
				}
			}
		}
	}

	public Users getOneUser(String email) {
		return dao.findByEmailAndGoogleFalse(email);
	}
	public Users Update( int id, String password, String oldPassword, String image) {

	Users tempUser=	dao.findById(id).get();
	System.err.println(psE().matches(oldPassword,tempUser.getPassword()));
	if (psE().matches(oldPassword,tempUser.getPassword())) {
		tempUser.setAvatar(image);
		tempUser.setPassword(psE().encode(password));
		dao.save(tempUser);
		return tempUser;
		
	}else {
		return null;
	}

		
	}
	public Users UpdateStatus(int id,Boolean status) {
		Users tempUser=	dao.findById(id).get();
		tempUser.setStatus(status);
		dao.save(tempUser);
		return tempUser;
	}
	public Users UpdateRole(int id,Boolean role) {
		System.err.println(role);
		Users tempUser=	dao.findById(id).get();
		tempUser.setRole(role);
		dao.save(tempUser);
		return tempUser;
	}
	public Users forgotPassword(String email,  String password) {
	Users tempUser=	dao.findByEmailAndGoogleFalse(email);
	tempUser.setPassword(psE().encode(password));
	dao.save(tempUser);
	return tempUser;
		
	}
	public List<Users> getAllUser(){
		return dao.findAll();
	}
	
	}



