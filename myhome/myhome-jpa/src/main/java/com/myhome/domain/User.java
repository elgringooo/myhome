package com.myhome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class User implements IModel<Integer> {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "login", nullable = false)
	private String username;

	@Column(name = "name", nullable = false)
	private String name;

	private int age;

	public User() {

	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public User(Integer id, String name, int age) {
		this(name, age);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name
				+ "]";
	}

}