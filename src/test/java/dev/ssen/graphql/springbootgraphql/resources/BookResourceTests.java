package dev.ssen.graphql.springbootgraphql.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import graphql.ExecutionResult;

@SpringBootTest
class BookResourceTests {

	@Autowired
	BookResource bookResource;

	@Test
	void testGetBooks_allBooks() {
		ResponseEntity<Object> response = bookResource.getBooks("{ allBooks { isbn name } }");
		assertResponse(response);
	}

	@Test
	void testgetBooks_bookById() {
		ResponseEntity<Object> response = bookResource.getBooks("{ bookById(id:\"1288\") { name authors} }");
		assertResponse(response);
	}

	private void assertResponse(ResponseEntity<Object> response) {
		assertThat(response.getStatusCode().equals(200));
		assertNotNull(response.getBody(), "no books returned by GraphQL API");
		assertTrue(((ExecutionResult) response.getBody()).getErrors().isEmpty(), "GraphQL response has errors");
	}

}
