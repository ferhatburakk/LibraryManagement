package com.library.management.model.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LoanDto {
    private Long id;
    private BookDto bookDto;
    private MemberDto memberDto;
    private LocalDate loanDate;
    private LocalDate returnDate;
}
