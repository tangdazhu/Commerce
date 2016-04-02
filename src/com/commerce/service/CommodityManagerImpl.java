package com.commerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.commerce.dao.CommodityDAO;
import com.commerce.model.Category;
import com.commerce.model.Commodity;
import com.commerce.model.vo.CommodityVO;

@Component
@Service
public class CommodityManagerImpl implements CommodityManager {
	@Autowired
	private CommodityDAO commodityDAO;

	public Commodity getCommodityById(int id) {
		Commodity c = commodityDAO.getCommodityById(id);

		return c;
	}

	public Commodity getCommodityByCategory(String name) {
		Commodity c = commodityDAO.getCommodityByCategory(name);

		return c;

	}

	@Override
	public List<Commodity> listAllCommodities() {
		List c = new ArrayList();
		List<Commodity> list = commodityDAO.listAllCommodities();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Commodity o = list.get(i);

				CommodityVO co = new CommodityVO(o.getId(), o.getCategory().getName(), o.getName(), o.getDescription());
				c.add(co);
			}
		}
		return c;
	}

	@Override
	public List<Commodity> listCommoditiesByPage(int pageNumber, int pageSize) {
		List c = new ArrayList();
		List list = commodityDAO.listCommoditiesByPage(pageNumber, pageSize);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Object[] o = (Object[]) list.get(i);

				CommodityVO co = new CommodityVO(new Integer(o[0].toString()).intValue(), o[3].toString(),
						o[1].toString(), o[2].toString());
				c.add(co);
			}
		}
		return c;
	}

	@Override
	public int getSizeOfCommodities(String query) {
		return commodityDAO.getSizeOfCommodities(query);
	}
}
