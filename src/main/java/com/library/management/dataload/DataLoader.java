package com.library.management.dataload;

import com.library.management.model.dto.BookDto;
import com.library.management.model.dto.LoanDto;
import com.library.management.model.dto.MemberDto;
import com.library.management.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader {

    private final LoanService loanService;
    private final BookService bookService;
    private final MemberService memberService;

    public DataLoader(LoanService loanService, BookService bookService, MemberService memberService) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @PostConstruct
    public void init() {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("ferhat@gmail.com");
        memberDto.setName("Ferhat");
        memberDto.setPassword("123");
        memberDto.setRole("USER");
        memberService.save(memberDto);

        BookDto bookDto = new BookDto();
        bookDto.setAuthor("TestAuthor");
        bookDto.setTitle("TestTitle");
        bookDto.setIsbn("TestIsbn");
        bookService.save(bookDto);

        LoanDto loanDto = new LoanDto();
        loanDto.setMemberDto(memberDto);
        loanDto.setBookDto(bookDto);
        loanDto.setLoanDate(LocalDate.now());
        loanService.save(loanDto);

    }
}
