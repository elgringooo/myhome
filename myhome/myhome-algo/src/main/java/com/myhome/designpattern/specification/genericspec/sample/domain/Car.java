package com.myhome.designpattern.specification.genericspec.sample.domain;

import java.util.Date;


public class Car {

  private Color color;
  private boolean convertible;
  private Person owner;
  private Date manufacturingDate;


  public Color color() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public boolean isConvertible() {
    return convertible;
  }

  public void setConvertible(boolean convertible) {
    this.convertible = convertible;
  }

  public Person owner() {
    return owner;
  }

  public void setOwner(Person owner) {
    this.owner = owner;
  }

  public Date manufacturingDate() {
    return manufacturingDate;
  }

  public void setManufacturingDate(Date manufacturingDate) {
    this.manufacturingDate = manufacturingDate;
  }


  public String toString() {
    return "Car{" +
       "color=" + color +
       ", convertible=" + convertible +
       ", owner=" + owner +
       ", manufacturingDate=" + manufacturingDate +
       '}';
  }
}
