package com.myhome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "ADDRESS")
    private String address;
    
    @OneToOne(mappedBy="address")
    private Company company;

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final String getAddress() {
        return address;
    }

    public final void setAddress(String address) {
        this.address = address;
    }

    public final Company getCompany() {
        return company;
    }

    public final void setCompany(Company company) {
        this.company = company;
    }

}
