package com.library.management.contracts.request;

import com.library.management.model.dto.BookDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NonExistingBookLoanRequest {

    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Book is required")
    private BookDto bookDto;

}
