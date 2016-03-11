package com.commerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.commerce.dao.UserDAO;
import com.commerce.model.User;
@Component
@Service
public class UserManagerImpl implements UserManager {
	@Autowired
	private UserDAO userDAO;

	public User getUserByEmail(String email) {
		User user = userDAO.getUserByEmail(email);

		return user;

	}

}
