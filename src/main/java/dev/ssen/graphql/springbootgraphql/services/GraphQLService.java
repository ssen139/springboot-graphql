package dev.ssen.graphql.springbootgraphql.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import dev.ssen.graphql.springbootgraphql.model.Book;
import dev.ssen.graphql.springbootgraphql.repositories.BookRepository;
import dev.ssen.graphql.springbootgraphql.services.dataFetchers.AllBooksDataFetcher;
import dev.ssen.graphql.springbootgraphql.services.dataFetchers.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Autowired
	AllBooksDataFetcher allBooksDataFetcher;

	@Autowired
	BookDataFetcher bookDataFetcher;

	@Autowired
	BookRepository bookRepository;

	@Value("classpath:books.graphql")
	private Resource resource;

	private GraphQL graphQL;

	@Bean
	public GraphQL graphQL() {
		return graphQL;
	}

	@PostConstruct
	public void loadSchema() throws IOException {
		loadDataintoHSQL();
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeDefnRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRunTimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefnRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRunTimeWiring() {
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allBooks", allBooksDataFetcher)
				.dataFetcher("bookById", bookDataFetcher))
				.build();
	}

	private void loadDataintoHSQL() {
		System.out.println("loading sample data into HSQL DB");
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book("1288", "Harry Potter and the Sorcerer's Stone", new String[] {"J.K.Rowling"})
				.setPublisher("Bloomsberg").setPageCount(300));
		bookList.add(new Book("5851", "Pride and Prejudice", new String[]{"Jane Austen"}));
		bookList.add(new Book("7894", "The Great Gatsby", new String[] {"F. Scott Fitzgerald"})
				.setPublisher("Charles Scribner’s Sons"));
		bookList.add(new Book("4162", "Good Omens", new String[] {"Neil Gaiman", "Terry Pratchett"}));

		bookRepository.saveAll(bookList);
	}

}
