package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Wallet;

public interface WalletDAO extends JpaRepository<Wallet, Integer> {
	@Query(value = "SELECT * FROM memetube.wallet where iduser=?1", nativeQuery = true)
	Wallet findWalletByIdUser(int iduser);

	@Modifying
	@Transactional
	@Query(value = "UPDATE wallet set balance=?1 where iduser=?2", nativeQuery = true)
	void updateBalance(Double balance, int iduser);
	
	@Modifying
	@Transactional
	@Query(value = "insert into wallet(iduser,balance) value (?1,?2)", nativeQuery = true)
	void postBalance(int iduser,Double balance);
}
