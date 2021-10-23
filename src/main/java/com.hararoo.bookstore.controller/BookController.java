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
   
 * @author Sandhya Nayak 22/10/2021
**/
@RestController
public class BookController {

   private BookService bookService;
   
   public BookController(BookService bookService) {
     this.bookService = bookService;
   } 

   @Value("#{${promotionalCodes}}") 
   private HashMap<String , String> promotionalCodes;

   @RequestMapping("/books")
   public List<Book> getBooks() {
    return bookService.getBooks();
   }
   

   @RequestMapping("/books/{id}")  
   public Optional<Object> getBook(@PathVariable String id) {
    return Optional.of(bookService.getBook(id));
   }

   @RequestMapping(value="/books/{id}" , method=RequestMethod.DELETE)  
   public  void deleteBook(@PathVariable String id) {
     bookService.deleteBook(id);
   }

   
   @RequestMapping(value="/books" , method = RequestMethod.POST)
   public void addBook(@RequestBody Book book) {
     bookService.addBook(book);
   }

  @RequestMapping(value="/books/{id}" , method = RequestMethod.PUT)
   public void updateBook(@RequestBody Book book) {
     bookService.updateBook(book);
   }


   @RequestMapping(value="/books/checkout" , method = RequestMethod.POST)
   public String checkOutBooks(@RequestBody List<Book> books) {
     return bookService.checkOutBooks(books , promotionalCodes);
   }
   
}