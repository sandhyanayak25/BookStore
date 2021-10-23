package com.hararoo.bookstore.database;

import com.hararoo.bookstore.domain.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Book Repository doing CRUD operations
   
 * @author Sandhya Nayak 22/10/2021
**/
public interface BookRepository extends CrudRepository<Book , String> {
}