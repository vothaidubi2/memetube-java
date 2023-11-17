package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryDAO;
import com.entity.Category;
import com.entity.Users;

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
	public Category UpdateStatus(int id,Boolean status) {
		Category tempStatus=	dao.findById(id).get();
		tempStatus.setStatus(status);
		dao.save(tempStatus);
		return tempStatus;
	}
	public Category updateCategory (Category category) {
		Category temp=dao.findById(category.getIdcategory()).get();
		temp.setName(category.getName());
		temp.setStatus(category.getStatus());
		dao.save(temp);
		return temp;
	}
	public Category addCategory(String nameCategory) {
		Category tempCategory=new Category();
		tempCategory.setName(nameCategory);
		tempCategory.setStatus(true);
		dao.save(tempCategory);
		return tempCategory;
	}
	
}
