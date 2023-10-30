package com.dao;

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
}