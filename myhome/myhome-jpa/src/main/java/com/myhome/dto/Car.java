package com.myhome.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Car {

	private String manufacturer;
	@Pattern(regexp="^(\\d{2})(\\d{2})$")
	private String expirateDate;
	
	@AfterTomorrow
	private Date date;

	@NotNull
	@Size(min = 2, max = 14)
	private String licensePlate;

	@Min(2)
	private int seatCount;

	public Car(String manufacturer, String licencePlate, int seatCount) {
		this.manufacturer = manufacturer;
		this.licensePlate = licencePlate;
		this.seatCount = seatCount;
	}

	@NotNull
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public String getExpirateDate() {
		return expirateDate;
	}

	public void setExpirateDate(String expirateDate) {
		this.expirateDate = expirateDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// getters and setters ...

}