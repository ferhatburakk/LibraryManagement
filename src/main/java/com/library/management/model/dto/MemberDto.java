package com.library.management.model.dto;

import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
}
