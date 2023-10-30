package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDAO;
import com.dao.WalletDAO;
import com.entity.Wallet;

@Service
public class WalletService {
	@Autowired
	WalletDAO walletDao;

	public void updateBalance(Double balance, int idUser) {
		walletDao.updateBalance(balance, idUser);
	}
	
	public void postBalance(int idUser,Double balance) {
		walletDao.postBalance(idUser,balance);
	}

	public Wallet findByidUser(int idUser) {
		return walletDao.findWalletByIdUser(idUser);
	}
}
