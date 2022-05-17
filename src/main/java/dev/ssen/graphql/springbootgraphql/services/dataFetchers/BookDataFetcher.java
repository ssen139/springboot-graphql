package dev.ssen.graphql.springbootgraphql.services.dataFetchers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.ssen.graphql.springbootgraphql.model.Book;
import dev.ssen.graphql.springbootgraphql.repositories.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Optional<Book>>{

	@Autowired
	BookRepository bookRepository;

	@Override
	public Optional<Book> get(DataFetchingEnvironment environment) {
		String isbn = environment.getArgument("id");
		return bookRepository.findById(isbn);
	}
	
	
}
