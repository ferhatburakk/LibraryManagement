package com.library.management.service;

import com.library.management.mapper.BookMapper;
import com.library.management.model.dto.BookDto;
import com.library.management.model.entity.Book;
import com.library.management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto save(BookDto bookDto) {
        return BookMapper.toBookDto(bookRepository.save(BookMapper.toBookEntity(bookDto)));
    }

    public BookDto getById(Long id) {
        return BookMapper.toBookDto(bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with given Id.")));
    }

    public BookDto getByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        return book.map(BookMapper::toBookDto).orElse(null);
    }

    public BookDto getByTitle(String title) {
        return BookMapper.toBookDto(bookRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Book not found with given Title.")));
    }

    public List<BookDto> getByAuthor(String author) {
        return BookMapper.toBookDtoList(bookRepository.findByAuthor(author));
    }

    public BookDto updateAvailability(BookDto bookDto) {
        BookDto result = getByIsbn(bookDto.getIsbn());
        result.setAvailable(bookDto.isAvailable());
        result = save(result);

        return result;
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDto> getAll() {
        return BookMapper.toBookDtoList(bookRepository.findAll());
    }
}
