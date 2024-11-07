package com.library.management.controller;

import com.library.management.contracts.request.LoanRequest;
import com.library.management.contracts.request.NonExistingBookLoanRequest;
import com.library.management.model.dto.LoanDto;
import com.library.management.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/save")
    public ResponseEntity<LoanDto> save(@RequestBody LoanDto loanDto) {
        return ResponseEntity.ok(loanService.save(loanDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<LoanDto>> getAll(){
        return ResponseEntity.ok(loanService.getAll());
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<List<LoanDto>> getByEmail(@RequestHeader String email){
        return ResponseEntity.ok(loanService.getLoansByMemberEmail(email));
    }

    @PostMapping("/save2")
    public ResponseEntity<LoanDto> save2(@Valid @RequestBody LoanRequest loanRequest){
        return ResponseEntity.ok(loanService.saveByBookIsbnAndMemberEmail(loanRequest));
    }

    @PutMapping("/updateReturnDate")
    public ResponseEntity<Boolean> updateReturnDate(@Valid @RequestBody LoanRequest loanRequest){
        return ResponseEntity.ok(loanService.updateReturnDate(loanRequest));
    }

    @PostMapping("/saveBookAndLoan")
    public ResponseEntity<LoanDto> saveBookAndLoan(@Valid @RequestBody NonExistingBookLoanRequest nonExistingBookLoanRequest){
        return ResponseEntity.ok(loanService.saveNonExistingBookAndLoan(nonExistingBookLoanRequest));
    }


}
