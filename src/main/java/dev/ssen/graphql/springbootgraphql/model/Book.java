package dev.ssen.graphql.springbootgraphql.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Book {

	@Id
	private String isbn;
	private String name;
	private String[] authors;
	private String publisher;
	private Integer pageCount;	
	
	public Book(){}
	
	public Book(String isbn, String name, String[] authors) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.authors = authors;
	}

	public Book setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}
	
	public Book setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
		return this;
	}

	
	public String getIsbn() {
		return isbn;
	}

	public String getName() {
		return name;
	}

	public String[] getAuthors() {
		return authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public Integer getPageCount() {
		return pageCount;
	}

}
