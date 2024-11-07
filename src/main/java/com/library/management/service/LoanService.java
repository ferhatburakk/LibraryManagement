package com.library.management.service;

import com.library.management.contracts.request.LoanRequest;
import com.library.management.contracts.request.NonExistingBookLoanRequest;
import com.library.management.mapper.BookMapper;
import com.library.management.mapper.LoanMapper;
import com.library.management.mapper.MemberMapper;
import com.library.management.model.dto.BookDto;
import com.library.management.model.dto.LoanDto;
import com.library.management.model.dto.MemberDto;
import com.library.management.model.entity.Book;
import com.library.management.model.entity.Loan;
import com.library.management.model.entity.Member;
import com.library.management.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberService memberService;
    private final BookService bookService;

    public LoanService(LoanRepository loanRepository, MemberService memberService, BookService bookService) {
        this.loanRepository = loanRepository;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    public LoanDto save(LoanDto loanDto) {
        String isbn = loanDto.getBookDto().getIsbn();
        String email = loanDto.getMemberDto().getEmail();
        Loan loan;
        try {
            BookDto bookDto = bookService.getByIsbn(isbn);
            MemberDto memberDto = memberService.getByEmail(email);
            Book book = BookMapper.toBookEntity(bookDto);
            Member member = MemberMapper.toMemberEntity(memberDto);
            loan = LoanMapper.toLoanEntity(loanDto);
            loan.setBook(book);
            loan.setMember(member);
            log.info("loan object -> " + loan.toString());
        } catch (Exception e) {
            throw new RuntimeException("Member or Book not found");
        }

        return LoanMapper.toLoanDto(loanRepository.save(loan));
    }

    public LoanDto saveByBookIsbnAndMemberEmail(LoanRequest loanRequest) {
        BookDto bookDto = bookService.getByIsbn(loanRequest.getIsbn());
        MemberDto memberDto = memberService.getByEmail(loanRequest.getEmail());
        Book book = BookMapper.toBookEntity(bookDto);
        Member member = MemberMapper.toMemberEntity(memberDto);
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setMember(member);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(null);
        log.info("loan object -> " + loan);
        return LoanMapper.toLoanDto(loanRepository.save(loan));
    }

    public List<LoanDto> getAll() {
        return LoanMapper.toLoanDtoList(loanRepository.findAll());
    }

    public List<LoanDto> getLoansByMemberEmail(String email) {
        return LoanMapper.toLoanDtoList(loanRepository.findByMemberEmail(email));
    }

    public boolean updateReturnDate(LoanRequest loanRequest) {

        BookDto bookDto = bookService.getByIsbn(loanRequest.getIsbn());
        MemberDto memberDto = memberService.getByEmail(loanRequest.getEmail());

        List<LoanDto> loans = getLoansByMemberEmail(loanRequest.getEmail());

        Optional<LoanDto> loanDto = loans.stream()
                .filter(n -> n.getBookDto().getIsbn().equals(loanRequest.getIsbn()))
                .findFirst();

        if (loanDto.isPresent()) {
            LoanDto loan = loanDto.get();
            Loan loanEntity = loanRepository.findById(loan.getId()).orElseThrow(() -> new RuntimeException("Loan not found with given Id."));
            loanEntity.setReturnDate(LocalDate.now());
            loanRepository.save(loanEntity);
            return true;
        }
        return false;
    }

    public LoanDto saveNonExistingBookAndLoan(NonExistingBookLoanRequest nonExistingBookLoanRequest) {
        LoanDto loanDto = new LoanDto();
        BookDto bookDto = nonExistingBookLoanRequest.getBookDto();
        bookDto.setAvailable(false);
        MemberDto memberDto = memberService.getByEmail(nonExistingBookLoanRequest.getEmail());

        if (bookService.getByIsbn(bookDto.getIsbn()) == null) {
            bookService.save(bookDto);
            loanDto.setMemberDto(memberDto);
            loanDto.setBookDto(bookDto);
            loanDto.setLoanDate(LocalDate.now());
            loanDto.setReturnDate(null);
            save(loanDto);
        } else {
            loanDto.setMemberDto(memberDto);
            loanDto.setBookDto(bookDto);
            loanDto.setLoanDate(LocalDate.now());
            loanDto.setReturnDate(null);
            save(loanDto);
        }
        return loanDto;
    }
}
