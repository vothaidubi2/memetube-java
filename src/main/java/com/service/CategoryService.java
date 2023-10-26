package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryDAO;
import com.entity.Category;

@Service
public class CategoryService {
	@Autowired
	CategoryDAO dao;
	
	public List<Category> getAll(){
		return dao.findAll();
	}
	
	public Category getById(int id) {
		return dao.findById(id).get();
	}
}
