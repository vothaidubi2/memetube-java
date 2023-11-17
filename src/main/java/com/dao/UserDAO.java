package com.dao;
import com.dto.UserDto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Users;

public interface UserDAO extends JpaRepository<Users, Integer> {
	public Boolean existsByEmail(String email);

	public Users findByEmail(String email);

	public Users findByEmailAndGoogleFalse(String email);

	public Users findByEmailAndGoogleTrue(String email);
	@Query("SELECT new com.dto.UserDto(u.iduser, u.email, u.datecreated, u.status, u.role, COALESCE(w.balance, 0)) FROM Users u LEFT JOIN Wallet w ON u.iduser = w.users.iduser")
	List<UserDto> findAllUserBalance();






}