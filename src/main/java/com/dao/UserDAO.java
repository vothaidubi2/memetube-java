package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Users;

public interface UserDAO extends JpaRepository<Users, Integer>{

}
