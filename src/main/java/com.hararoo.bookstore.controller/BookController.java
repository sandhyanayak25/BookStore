package com.hararoo.bookstore.controller;

import com.hararoo.bookstore.domain.Book;
import com.hararoo.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


/**
 * Controller with different method defining different operations.
 *
 * @author Sandhya Nayak 22/10/2021
 **/
@RestController
public class BookController {

    private BookService bookService;

    /**
     * Constructor
     *
     * @param bookService
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Value("#{${promotionalCodes}}")
    private HashMap<String, String> promotionalCodes;

    /**
     * Get books method that returns books.
     *
     * @return
     */
    @RequestMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    /**
     * Get all books with Id.
     *
     * @param id
     * @return
     */
    @RequestMapping("/books/{id}")
    public Optional<Object> getBook(@PathVariable String id) {
        return Optional.of(bookService.getBook(id));
    }

    /**
     * Delete books by Id.
     *
     * @param id
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }

    /**
     * Post book object.
     *
     * @param book
     */
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    /**
     * Update book object with id.
     *
     * @param book
     */
    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }


    /**
     * CheckOut the books.
     *
     * @param books
     * @return
     */
    @RequestMapping(value = "/books/checkout", method = RequestMethod.POST)
    public String checkOutBooks(@RequestBody List<Book> books) {
        return bookService.checkOutBooks(books, promotionalCodes);
    }

}