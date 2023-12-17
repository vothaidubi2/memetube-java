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

import com.dao.ChannelDAO;
import com.dao.UserDAO;
import com.dao.WalletDAO;
import com.dto.UserDto;
import com.dto.UserLoginDto;
import com.entity.Channel;
import com.entity.Users;
import com.entity.Wallet;
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
	@Autowired
	private WalletDAO walletDao;
	@Autowired ChannelDAO channelDao;
	
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
					Wallet wallet=new Wallet();
					wallet.setUsers(user);
					wallet.setBalance(0);
					walletDao.save(wallet);
					Channel channel=new Channel();
					int atIndex=user.getEmail().indexOf('@');
					channel.setChannelname(user.getEmail().substring(0,atIndex));
					channel.setDatecreated(currentDate);
					channel.setStatus(true);
					channel.setUsers(user);
					channelDao.save(channel);
					
				}
			} else {
				if (dao.findByEmailAndGoogleFalse(user.getEmail()) == null) {
					Date currentDate = new Date();
					user.setDatecreated(currentDate);
					user.setPassword(psE().encode(user.getPassword()));
					dao.save(user);
//					Wallet wallet=new Wallet();
//					wallet.setUsers(user);
//					wallet.setBalance(0);
//					walletDao.save(wallet);
					Channel channel=new Channel();
					int atIndex=user.getEmail().indexOf('@');
					channel.setChannelname(user.getEmail().substring(0,atIndex));
					channel.setDatecreated(currentDate);
					channel.setStatus(true);
					channel.setUsers(user);
					channelDao.save(channel);
				}
			}
		}
	}

	public Users getOneUser(String email) {
		return dao.findByEmailAndGoogleFalse(email);
	}
	public Users Update( int id, String password, String oldPassword, String image) {

	Users tempUser=	dao.findById(id).get();
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
	public List<UserDto> getAllUser(){
		return dao.findAllUserBalance();
	}

	}



