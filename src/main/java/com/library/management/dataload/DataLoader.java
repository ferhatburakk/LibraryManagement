package com.library.management.dataload;

import com.library.management.model.dto.BookDto;
import com.library.management.model.dto.LoanDto;
import com.library.management.model.dto.MemberDto;
import com.library.management.model.entity.Authorities;
import com.library.management.model.entity.Users;
import com.library.management.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader {

    private final LoanService loanService;
    private final BookService bookService;
    private final MemberService memberService;
    private final UsersService usersService;
    private final AuthoritiesService authoritiesService;

    public DataLoader(LoanService loanService, BookService bookService, MemberService memberService, UsersService usersService, AuthoritiesService authoritiesService) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.memberService = memberService;
        this.usersService = usersService;
        this.authoritiesService = authoritiesService;
    }

    @PostConstruct
    public void init() {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("ferhat@gmail.com");
        memberDto.setName("Ferhat");
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

        Users users = new Users();
        users.setEnabled("1");
        users.setPassword("123");
        users.setUsername("ferhat");
        usersService.save(users);

        Authorities authorities = new Authorities();
        authorities.setAuthority("read");
        authorities.setUsername("ferhat");
        authoritiesService.save(authorities);
    }
}
