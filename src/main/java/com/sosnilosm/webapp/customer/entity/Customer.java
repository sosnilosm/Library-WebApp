package com.sosnilosm.webapp.customer.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Date;

/**
 * @author Sergei Sosnilo
 */
public class Customer {
    private int id;
    @NotEmpty(message = "Empty required field")
    private String firstname;
    @NotEmpty(message = "Empty required field")
    private String lastname;
    @NotEmpty(message = "Empty required field")
    @Email(message = "Introduced invalid email")
    private String email;
    private Date bdate;
    @NotEmpty(message = "Empty required field")
    private String passport;
    @Pattern(regexp = "|(\\d+, \\w+, \\w+, \\w+)", message = "Incorrect address format. Format: {house number (int)}, " +
            "{street's name (varchar)}, {city's name (varchar)}, {country's name (varchar)}")
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
