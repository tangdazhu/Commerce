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

		try {
			c = (Commodity) manager
					.createQuery(
							"select a.name, a.description from Commodity a, Category b where a.category_id =b.id and b.name=:name")
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			c = null;
		}
		;

		return c;
	}

	@Override
	public List listAllCommodities() {

		List c = new ArrayList();

		try {
			c = manager.createQuery("from Commodity a order by id ASC").getResultList();
			;
		} catch (NoResultException e) {
			c = null;
		}
		;

		return c;
	}

	@Override
	public int getSizeOfCommodities(String query) {
		int size;

		try {
			Object o = manager.createNativeQuery("select count(*) from commodity").getSingleResult();

			size = new Integer(o.toString());

		} catch (NoResultException e) {
			size = 0;
		}
		;

		return size;
	}

	@Override
	public List listCommoditiesByPage(int pageNumber, int pageSize) {
		List list = new ArrayList();

		try {
			list = manager.createNativeQuery(
					"select id,name,description,category_id from commodity a order by a.id ASC limit " + pageSize
							+ " offset " + (pageNumber - 1) * pageSize)
					.getResultList();

		} catch (NoResultException e) {

		}
		;

		return list;
	}

}
