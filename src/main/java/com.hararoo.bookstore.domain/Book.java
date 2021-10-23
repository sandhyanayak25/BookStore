package com.hararoo.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Model class of the application -- Book
   
 * @author Sandhya Nayak 22/10/2021
**/
@Entity
public class Book {

	@Id
	String id;

	String name;

	String description;

	String author;

	String classification;

	BigDecimal price;

	String isbn;

	public String getId() { return id;}

	public void setId(String id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name;}

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description ;}

	public String getAuthor() { return author; }

	public void setAuthor(String author) { this.author = author ; } 

	public String getClassification() { return classification ; }

	public void setClassification(String classification) { this.classification = classification; }

	public BigDecimal getPrice() { return price; }

	public void setPrice(BigDecimal price) { this.price = price; }

	public String getIsbn() { return isbn; }

	public void setIsbn(String isbn) { this.isbn = isbn; }


	@Override
	public boolean equals(Object o) {
  		 if(this == o ) return true;

   		if(!(o instanceof Book)) return false;
   			Book book = (Book) o;
  			 return Objects.equals(getId()  , book.getId()) && 
     		    		Objects.equals(getName() , book.getName()) && 
         	     		     Objects.equals(getDescription() , 	book.getDescription()) &&
                      		     Objects.equals(getAuthor() , book.getAuthor()) &&
                    		     Objects.equals(getClassification() , book.getClassification()) && 
                    		     Objects.equals(getPrice() , book.getPrice()) &&
                   		     Objects.equals(getIsbn() , book.getIsbn());

		}

	@Override
	public int hashCode() {
 		  return Objects.hash(getId() , getName() ,getDescription() , getAuthor() , getClassification() , getPrice() , getIsbn());
	}

	@Override
	public String toString() {
  		 return "Book{ " +
    		       "id='" + id + '\'' +
      		     ",name='" +name + '\'' + 
        		   " , description= '" +description + '\''+
       		    ",author = '" +author + '\'' +
       		    ",classification = '" +classification + '\'' +
        	   " ,price = '" +price + '\'' +
        	   " , isbn = ' " + isbn + '\'' +
        	    '}' ;

	}

}
