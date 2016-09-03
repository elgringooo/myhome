package com.myhome.designpattern.specification.genericspec.sample.domain;

public class Person {

  private Address homeAddress;


  public Address homeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(Address homeAddress) {
    this.homeAddress = homeAddress;
  }


  public String toString() {
    return "Person{" +
       "homeAddress=" + homeAddress +
       '}';
  }
}
