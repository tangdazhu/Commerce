package com.commerce.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.model.Commodity;

@Repository
@Transactional
public class CommodityDAOImpl implements CommodityDAO {

	@PersistenceContext()
	private EntityManager manager;


	@Override
	public Commodity getCommodityByCategory(String name) {

		Commodity c;
		
	try{
		 c = (Commodity) manager
				.createQuery("select a.name, a.description from Commodity a, Category b where a.category_id =b.id and b.name=:name")
				.setParameter("name", name).getSingleResult();
	}catch(NoResultException e){
		c=null;
	};

		return c;
	}


	@Override
	public List listAllCommodities() {

		List c=new ArrayList();
		
	try{
		 c =  manager
				.createQuery("from Commodity a").getResultList();			;
	}catch(NoResultException e){
		c=null;
	};

		return c;
	}

}
