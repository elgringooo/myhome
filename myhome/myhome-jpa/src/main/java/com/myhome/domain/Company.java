package com.myhome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company {
<<<<<<< HEAD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "EMPLOYEES")
	private long employees;
	@Column(name = "HEADOFFICE")
	private String headoffice;
	@OneToOne
	private Address address;
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMPLOYEES")
    private long employees;
    @Column(name = "HEADOFFICE")
    private String headoffice;
>>>>>>> refs/remotes/origin/master

    @OneToOne
    private Address address;

    public Company() {
    }

    public Company(String name, long employees, String headoffice) {
        this.name = name;
        this.employees = employees;
        this.headoffice = headoffice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmployees(long employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEmployees() {
        return employees;
    }

    public String getHeadoffice() {
        return headoffice;
    }

<<<<<<< HEAD
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", employees=" + employees + ", headoffice=" + headoffice + "]";
	}

}
=======
    public void setHeadoffice(String headoffice) {
        this.headoffice = headoffice;
    }

    @Override
    public String toString() {
        return "Company [id=" + id + ", name=" + name + ", employees=" + employees + ", headoffice=" + headoffice + "]";
    }

    public final Address getAddress() {
        return address;
    }

    public final void setAddress(Address address) {
        this.address = address;
    }

}
>>>>>>> refs/remotes/origin/master
