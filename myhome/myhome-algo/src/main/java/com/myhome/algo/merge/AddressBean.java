package com.myhome.algo.merge;

public class AddressBean {
	private String cityName;
	private String country;
	private String firstName;
	private String lastName;
	private DetailBean detail;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String county) {
		this.country = county;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DetailBean getDetail() {
		return detail;
	}

	public void setDetail(DetailBean detail) {
		this.detail = detail;
	}

}
