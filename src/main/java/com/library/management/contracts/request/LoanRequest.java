package com.library.management.contracts.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoanRequest {

    @NotBlank(message = "Book isbn is required")
    private String isbn;
    @Email(message = "Email should be valid")
    private String email;
}
