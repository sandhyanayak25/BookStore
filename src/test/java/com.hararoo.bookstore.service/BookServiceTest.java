package com.hararoo.bookstore.service;

import com.hararoo.bookstore.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookService bookService;


    /**
     *  Test adding of books.
     */
    @Test
    public void addBook() {
        Book book = createBook();
        bookService.addBook(book);
        Mockito.verify(bookService, times(1)).addBook(book);
    }

    /**
     * Test update books.
     */
    @Test
    public void updateBook() {
        Book book = createBook();
        bookService.updateBook(book);
        Mockito.verify(bookService, times(1)).updateBook(book);
    }

    /**
     *  Test delete books.
     */
    @Test
    public void deleteBook() {
        bookService.deleteBook("111");
        Mockito.verify(bookService, times(1)).deleteBook("111");
    }

    /**
     *  Test checkout books.
     */
    @Test
    public void checkOutBooks() {

        Book book1 = createBook();
        book1.setPrice(new BigDecimal(2000));

        Book book2 = createBook();
        book2.setPrice(new BigDecimal(1000));

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("ENGINEERING", "20");
        bookService.checkOutBooks(Arrays.asList(book1, book2), map);

        Mockito.verify(bookService, times(1)).checkOutBooks(Arrays.asList(book1, book2), map);
    }

    /**
     *  Create Books.
     * @return
     */
    private Book createBook() {
        Book book = new Book();
        book.setId("111");
        book.setIsbn("ISBN124");
        book.setDescription("Description");
        book.setAuthor("Author123");
        book.setClassification("Classification123");
        book.setPrice(new BigDecimal(1000));
        return book;
    }
}