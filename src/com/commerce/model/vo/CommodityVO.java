package com.commerce.model.vo;
// default package

// Generated Mar 7, 2016 5:03:17 PM by Hibernate Tools 3.4.0.CR1

import com.commerce.model.Category;

public class CommodityVO implements java.io.Serializable {

	private int id;
	private String categoryName;
	private String name;
	private String description;

	public CommodityVO(int id, String categoryName, String name, String description) {
		this.id = id;
		this.categoryName = categoryName;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
