package com.library.management.repository;

import com.library.management.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);
}
