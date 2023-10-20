package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Comment;

public interface CommentDAO extends JpaRepository<Comment, Integer>{

}
