package com.commerce.dao;

import com.commerce.model.User;

public interface UserDAO {
	public User getUserByEmail(String email);
}
