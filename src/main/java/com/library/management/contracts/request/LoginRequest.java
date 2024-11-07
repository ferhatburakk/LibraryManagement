package com.library.management.contracts.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email Boş Olamaz!")
    private String username;
    @NotBlank(message = "Şifre Boş Olamaz")
    private String password;
}
