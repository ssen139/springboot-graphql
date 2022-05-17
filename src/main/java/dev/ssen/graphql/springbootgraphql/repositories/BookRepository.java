package dev.ssen.graphql.springbootgraphql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ssen.graphql.springbootgraphql.model.Book;

public interface BookRepository extends JpaRepository<Book, String>{

}
