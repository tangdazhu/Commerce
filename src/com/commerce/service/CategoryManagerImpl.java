package com.commerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.commerce.dao.CategoryDAO;
import com.commerce.model.Category;
@Component
@Service
public class CategoryManagerImpl implements CategoryManager {
	@Autowired
	private CategoryDAO categoryDAO;


	public Category getCategoryByName(String name) {
		Category c=categoryDAO.getCategoryByName(name);
		return c;
	}

}
