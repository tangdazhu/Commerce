package com.commerce.service;

import java.util.List;

import com.commerce.model.Commodity;

public interface CommodityManager {

	public Commodity getCommodityByCategory(String name) ;
	
	public List<Commodity> listAllCommodities() ;
}
