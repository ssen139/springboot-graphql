package dev.ssen.graphql.springbootgraphql.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ssen.graphql.springbootgraphql.services.GraphQLService;
import graphql.ExecutionResult;

@RestController
@RequestMapping("/api/books")
public class BookResource {

	@Autowired
	GraphQLService graphQLService;
	
	@PostMapping
	public ResponseEntity<Object> getBooks(@RequestBody String query) {
		ExecutionResult result = graphQLService.graphQL().execute(query);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
