package com.commerce.model;
// default package
// Generated Mar 7, 2016 5:03:17 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category", catalog = "commercedb")
public class Category implements java.io.Serializable {

	private int id;
	private String name;
	private String description;
	private List<Commodity> commodities = new ArrayList<Commodity>(0);

	public Category() {
	}

	public Category(int id) {
		this.id = id;
	}

	public Category(int id, String name, String description,
			List<Commodity> commodities) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.commodities = commodities;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	@IndexColumn(name="id")
	public List<Commodity> getCommodities() {
		return this.commodities;
	}

	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

}
