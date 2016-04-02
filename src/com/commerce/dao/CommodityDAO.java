package com.commerce.dao;

import java.util.List;

import com.commerce.model.Commodity;

public interface CommodityDAO {
	public Commodity getCommodityByCategory(String name);

	public List listAllCommodities();

	public List listCommoditiesByPage(int pageNumber, int pageSize);

	public int getSizeOfCommodities(String query);

	public Commodity getCommodityById(int id);

}
