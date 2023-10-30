package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDAO;
import com.entity.Users;

@Service
public class WalletService {
	@Autowired
	UserDAO dao;
	
	public void updateBalance(Double balance,int idUser) {
		dao.updateBalance(balance,idUser);
	}
	
	public Users findByidUser(int idUser) {
		return dao.findById(idUser).get();
	}
}
