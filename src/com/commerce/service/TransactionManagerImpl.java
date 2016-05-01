package com.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.commerce.dao.TransactionDAO;
import com.commerce.model.Transaction;

@Component
@Service
public class TransactionManagerImpl implements TransManager {
	@Autowired
	private TransactionDAO transDAO;

	@Override
	public int insertTrans(Transaction t) {
		// TODO Auto-generated method stub
		return transDAO.insertTrans(t);
	}

}
