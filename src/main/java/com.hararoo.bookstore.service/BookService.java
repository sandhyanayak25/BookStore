package com.hararoo.bookstore.service;

import com.hararoo.bookstore.database.BookRepository;
import com.hararoo.bookstore.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Book Service extending the service class for the controller.
   
 * @author Sandhya Nayak 22/10/2021
**/
@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  private static final String FICTION="FICTION";
  private static final String COMIC ="COMIC" ;
  private static final String ENGINEERING ="ENGINEERING";
  private static final String NOTEBOOK = "NOTEBOOK";

        
 	public List<Book> getBooks() {
 		 List<Book> allBooks = new ArrayList<> ();
 		 bookRepository.findAll().forEach(allBooks::add);
 		 return allBooks;
 	}

 	public Optional<Object> getBook(final String bookId){
  		 if(bookRepository.findById(bookId).isPresent()) {
  		   return Optional.of(bookRepository.findById(bookId).get());
 		  }else {
  		   return Optional.of("No books for bookId "+bookId);
  	 }

 	} 

	 public void addBook(Book book) {
 		  bookRepository.save(book);
 	 }
 
	 public void updateBook(Book book) {
		  bookRepository.save(book);
	 }

 	public void deleteBook(String bookId) {
 		 Book book = new Book();
 		 book.setId(bookId);
  		bookRepository.delete(book);

	} 


	public String checkOutBooks(List<Book> bookList , HashMap<String , String> promotionalCodes) {
 		BigDecimal totalAmount =  new BigDecimal(0);
 		for(Book book : bookList) {
  		if(book.getClassification().equals(FICTION)) {
  			  BigDecimal promotionalCode = new BigDecimal(promotionalCodes.get(FICTION));
  			  BigDecimal discountAmount = book.getPrice().multiply(promotionalCode).divide(new BigDecimal(100));
  			  totalAmount = book.getPrice().subtract(discountAmount);
  		} else if(book.getClassification().equals(COMIC)) {
    			BigDecimal promotionalCode = new BigDecimal(promotionalCodes.get(COMIC));
  		        BigDecimal discountAmount = book.getPrice().multiply(promotionalCode).divide(new BigDecimal(100));
  			  totalAmount = book.getPrice().subtract(discountAmount);
  		} else if(book.getClassification().equals(ENGINEERING)) {
   			 BigDecimal promotionalCode = new BigDecimal(promotionalCodes.get(ENGINEERING));
    			BigDecimal discountAmount = book.getPrice().multiply(promotionalCode).divide(new BigDecimal(100));
   			 totalAmount = book.getPrice().subtract(discountAmount);
  		} else if(book.getClassification().equals(NOTEBOOK)) {
  			  BigDecimal promotionalCode = new BigDecimal(promotionalCodes.get(NOTEBOOK));
   			  BigDecimal discountAmount = book.getPrice().multiply(promotionalCode).divide(new BigDecimal(100));
   			  totalAmount = book.getPrice().subtract(discountAmount);
 		 } 
	    
	}
   return ("The total amount to be paid is "+totalAmount.toPlainString());
	}
}