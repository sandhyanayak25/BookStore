package com.hararoo.bookstore.controller;

import com.hararoo.bookstore.domain.Book;
import com.hararoo.bookstore.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for BookController.
 *
 * @author Sandhya Nayak 23/10/2021
 **/
@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    private BookController bookController;

    @Mock
    public BookService bookService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        bookController = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    /**
     *  Testing the post of books
     * @throws Exception
     */
    @Test
    public void should_post_books() throws Exception {
        MockHttpServletRequestBuilder request = post("/books")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "   \"id\" : \"123\" , \n" +
                        "   \"name\" : \"TEST_BOOK\",\n" +
                        "   \"author\" : \"TEST_AUTHOR\",\n" +
                        "   \"description\" : \"TEST DESCRIPTION\" ,\n" +
                        "   \"classification\" : \"TEST_CLASSIFICATION\",\n" +
                        "   \"isbn\" : \"TESTISBN123\" ,\n" +
                        "    \"price\" : \"2000\"\n" +
                        "}");

        ResultActions response = this.mockMvc.perform(request);
        response.andExpect(status().isOk());
    }

    /**
     *  Testing delete books.
     * @throws Exception
     */
    @Test
    public void should_delete_books() throws Exception {
        MockHttpServletRequestBuilder request = delete("/books/123")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions resp = this.mockMvc.perform(request);
        resp.andExpect(status().isOk());
    }

    /**
     * Test Put books.
     * @throws Exception
     */
    @Test
    public void should_put_books() throws Exception {
        MockHttpServletRequestBuilder request = post("/books")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "   \"id\" : \"123\" , \n" +
                        "   \"name\" : \"TEST_BOOK\",\n" +
                        "   \"author\" : \"TEST_AUTHOR\",\n" +
                        "   \"description\" : \"TEST DESCRIPTION\" ,\n" +
                        "   \"classification\" : \"TEST_CLASSIFICATION\",\n" +
                        "   \"isbn\" : \"TESTISBN123\" ,\n" +
                        "    \"price\" : \"2000\"\n" +
                        "} ");

        ResultActions response = this.mockMvc.perform(request);

        response.andExpect(status().isOk());
    }

    /**
     *  Test checkout books.
     */
    @Test
    public void should_checkout_books() {
        Book book1 = new Book();
        book1.setPrice(new BigDecimal(1000));
        Book book2 = new Book();
        book2.setPrice(new BigDecimal(1000));

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("FICTION", "20");

        when(bookService.checkOutBooks(any(), any())).thenReturn("The Total amount to be paid is 2010");
        String returnValue = bookController.checkOutBooks(bookList);
        assertEquals("The Total amount to be paid is 2010", returnValue);
    }

}