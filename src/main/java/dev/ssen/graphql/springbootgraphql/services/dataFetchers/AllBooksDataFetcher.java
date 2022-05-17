package dev.ssen.graphql.springbootgraphql.services.dataFetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.ssen.graphql.springbootgraphql.model.Book;
import dev.ssen.graphql.springbootgraphql.repositories.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> get(DataFetchingEnvironment environment) {
		return bookRepository.findAll();
	}

}
