package com.commerce.view;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class CommodityListJsonView {

	public CommodityListJsonView() {
	}

	
	public View getView() throws Exception {

		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setExtractValueFromSingleKeyModel(true);
		view.setPrettyPrint(true);
		
		return view;
	}	

}
