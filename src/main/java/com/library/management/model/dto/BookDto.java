package com.library.management.model.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable = true;  // Kitabın müsait olup olmadığı
}
