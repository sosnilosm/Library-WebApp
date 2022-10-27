package com.sosnilosm.webapp.book.dao;

import com.sosnilosm.webapp.book.entity.Book;
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

    public void insertNewBook(Book book) {
        jdbcTemplate.update(
                "INSERT INTO Book(name, author, year, amount) " +
                        "VALUES(?, ?, ?, ?)",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                book.getAmount()
        );
    }

    public void updateOneBook(Book book) {
        jdbcTemplate.update(
                "UPDATE Book SET name = ?, author = ?, year = ?, amount = ? WHERE id = ?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                book.getAmount(),
                book.getId()
        );
    }

    public void deleteOneBook(int id) {
        jdbcTemplate.update(
                "DELETE FROM Book WHERE id = ?",
                id
        );
    }

    public List<Book> selectCustomersBooks(int customer_id) {
        return jdbcTemplate.query(
                "SELECT B.* FROM Customer " +
                        "INNER JOIN customer_book cb on Customer.id = cb.customer_id " +
                        "LEFT JOIN Book B on B.id = cb.book_id " +
                        "WHERE Customer.id = ?",
                new Object[]{customer_id},
                new BeanPropertyRowMapper<>(Book.class)
        );
    }

    public void issueBookToCustomer(int customer_id, int book_id) {
        jdbcTemplate.update(
                "INSERT INTO customer_book(book_id, customer_id) VALUES (?, ?)",
                book_id,
                customer_id
        );
    }

    public void refundBookFromCustomer(int customer_id, int book_id) {
        jdbcTemplate.update(
                "DELETE FROM customer_book WHERE book_id = ? AND customer_id = ?",
                book_id,
                customer_id
        );
    }
}
