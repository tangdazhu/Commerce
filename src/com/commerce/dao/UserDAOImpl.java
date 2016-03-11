package com.commerce.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.model.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext()
	private EntityManager manager;

	public User getUserByEmail(String email) {
	
		User user;
		
	try{
		 user = (User) manager
				.createQuery("select a from User a where a.email=:email")
				.setParameter("email", email).getSingleResult();
	}catch(NoResultException e){
		user=null;
	};

		return user;

	}
}
