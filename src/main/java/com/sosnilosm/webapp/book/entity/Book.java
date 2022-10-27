package com.sosnilosm.webapp.book.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

/**
 * @author Sergei Sosnilo
 */
public class Book {
    private int id;
    @NotEmpty(message = "Empty required field")
    private String name;
    @NotEmpty(message = "Empty required field")
    private String author;
    private Date year;
    @Min(value = 0, message = "Must be bigger than 0")
    private int amount;

    public Book() {
    }

    public Book(int id, String name, String author, Date year, int amount) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
