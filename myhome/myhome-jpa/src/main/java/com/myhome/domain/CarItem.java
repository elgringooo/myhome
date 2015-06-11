package com.myhome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carItem")
public class CarItem implements IModel<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CAR_ID",nullable=false)
	private Car car;

 

	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getId() {
		return id;
	}



	public Car getCar() {
		return car;
	}



	public void setCar(Car car) {
		this.car = car;
	}



	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
	
	

	 
}
