package com.service;


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

import com.dao.UserDAO;
import com.entity.Users;
import com.impl.CusUserDetailsImpl;

@Service
@Primary
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDAO dao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//load user form database
		Users user= dao.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}else {
			return new  CusUserDetailsImpl(user);
		}

	}
	}






