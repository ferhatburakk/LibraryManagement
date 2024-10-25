package com.library.management.mapper;

import com.library.management.model.dto.BookDto;
import com.library.management.model.entity.Book;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BookMapper {

    public static BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setAvailable(book.isAvailable());
        bookDto.setId(book.getId());
        return bookDto;
    }

    public static Book toBookEntity(BookDto bookDto) {
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setAvailable(bookDto.isAvailable());
        book.setId(bookDto.getId());
        return book;
    }


    public static List<BookDto> toBookDtoList(List<Book> bookList) {
        return bookList.stream()
                .map(BookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    public static List<Book> toBookEntityList(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(BookMapper::toBookEntity)
                .collect(Collectors.toList());
    }

}
