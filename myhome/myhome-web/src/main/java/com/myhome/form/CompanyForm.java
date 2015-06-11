package com.myhome.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class CompanyForm {

	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String headoffice;
	
	@NotEmpty
	//@Pattern(regexp="\\d*")
	@Range(min = 1, max = 10000)
	@Digits(fraction = 0, integer = 4)
	private String employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadoffice() {
		return headoffice;
	}

	public void setHeadoffice(String headoffice) {
		this.headoffice = headoffice;
	}

	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}

}