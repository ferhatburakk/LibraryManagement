package com.library.management.mapper;

import com.library.management.model.dto.LoanDto;
import com.library.management.model.entity.Loan;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class LoanMapper {

    public static LoanDto toLoanDto(Loan loan) {
        LoanDto loanDto = new LoanDto();
        loanDto.setId(loan.getId());
        loanDto.setLoanDate(loan.getLoanDate());
        loanDto.setReturnDate(loan.getReturnDate());
        loanDto.setBookDto(BookMapper.toBookDto(loan.getBook()));
        loanDto.setMemberDto(MemberMapper.toMemberDto(loan.getMember()));
        return loanDto;
    }

    public static Loan toLoanEntity(LoanDto loanDto) {
        Loan loan = new Loan();
        loan.setId(loan.getId());
        loan.setLoanDate(loanDto.getLoanDate());
        loan.setReturnDate(loanDto.getReturnDate());
        loan.setBook(BookMapper.toBookEntity(loanDto.getBookDto()));
        loan.setMember(MemberMapper.toMemberEntity(loanDto.getMemberDto()));
        return loan;
    }

    public static List<LoanDto> toLoanDtoList(List<Loan> loanList) {
        return loanList.stream()
                .map(LoanMapper::toLoanDto)
                .collect(Collectors.toList());
    }

    public static List<Loan> toLoanEntityList(List<LoanDto> loanDtoList) {
        return loanDtoList.stream()
                .map(LoanMapper::toLoanEntity)
                .collect(Collectors.toList());
    }

}
