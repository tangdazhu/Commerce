package com.commerce.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.model.Transaction;

@Repository
@Transactional
public class TransactionDAOImpl implements TransactionDAO {
	@PersistenceContext()
	private EntityManager manager;

	@Override
	public int insertTrans(Transaction t) {
		manager.persist(t);
		manager.flush();
		return 1;
	}

}
