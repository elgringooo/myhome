package com.myhome.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car implements IModel<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "company")
	private String company;

	@Column(name = "model")
	private String model;

	@Column(name = "price")
	private long price;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
	private List<CarItem> items;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public List<CarItem> getItems() {
		return items;
	}

	public void setItems(List<CarItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", items=" + items + "]";
	}

}
