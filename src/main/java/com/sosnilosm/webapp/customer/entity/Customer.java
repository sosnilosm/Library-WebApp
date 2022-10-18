package com.sosnilosm.webapp.customer.entity;

import java.sql.Date;

/**
 * @author Sergei Sosnilo
 */
public class Customer {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private Date bdate;
    private String passport;
    private String address;

    public Customer() {
    }

    public Customer(int id, String firstname, String lastname, String email, Date bdate, String passport, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.bdate = bdate;
        this.passport = passport;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
