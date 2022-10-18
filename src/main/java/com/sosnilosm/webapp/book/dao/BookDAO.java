package com.sosnilosm.webapp.book.dao;

import com.sosnilosm.webapp.book.entity.Book;
import com.sosnilosm.webapp.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Sergei Sosnilo
 */
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> selectAllBooks() {
        return jdbcTemplate.query(
                "SELECT * FROM Book ORDER BY id",
                new BeanPropertyRowMapper<>(Book.class)
        );
    }

    public Book selectOneBook(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class) // BeanPropertyRowMapper(Customer.class)
        ).stream().findAny().orElse(null);
    }
}
