package com.commerce.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.model.Category;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@PersistenceContext(unitName="mysqlEntityManagerFactory")
	private EntityManager manager;


	@Override
	public Category getCategoryByName(String name) {

		Category c;
		
	try{
		 c = (Category) manager
				.createQuery("select a from Category a where a.name=:name")
				.setParameter("name", name).getSingleResult();
	}catch(NoResultException e){
		c=null;
	};

		return c;
	}
}
