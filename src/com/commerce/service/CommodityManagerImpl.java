package com.commerce.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.commerce.dao.CommodityDAO;
import com.commerce.model.Commodity;
@Component
@Service
public class CommodityManagerImpl implements CommodityManager {
	@Autowired
	private CommodityDAO commodityDAO;

	public Commodity getCommodityByCategory(String name) {
		Commodity c = commodityDAO.getCommodityByCategory(name);

		return c;

	}

	@Override
	public List<Commodity> listAllCommodities() {
		List l=commodityDAO.listAllCommodities();
		return l;
	}

}
