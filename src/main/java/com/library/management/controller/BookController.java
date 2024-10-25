package com.library.management.controller;

import com.library.management.model.dto.BookDto;
import com.library.management.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public ResponseEntity<BookDto> save(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @GetMapping("/getByIsbn/{isbn}")
    public ResponseEntity<BookDto> getByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getByIsbn(isbn));
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<BookDto> getByTitle(@RequestHeader String title) {
        return ResponseEntity.ok(bookService.getByTitle(title));
    }

    @GetMapping("/getByAuthor")
    public ResponseEntity<List<BookDto>> getByAuthor(@RequestHeader String author) {
        return ResponseEntity.ok(bookService.getByAuthor(author));
    }

    @PutMapping("/updateAvailability")
    public ResponseEntity<BookDto> updateAvailability(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.updateAvailability(bookDto));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok("Book deleted succesfully with given id: " + id);
    }
}
