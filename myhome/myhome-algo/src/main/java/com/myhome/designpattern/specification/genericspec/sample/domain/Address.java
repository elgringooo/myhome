package com.myhome.designpattern.specification.genericspec.sample.domain;

public class Address {

  Region region;

  public Region region() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }


  @Override
  public String toString() {
    return "Address{" +
       "region=" + region +
       '}';
  }
}
