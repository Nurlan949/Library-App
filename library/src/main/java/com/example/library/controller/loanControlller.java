package com.example.library.controller;

import com.example.library.entity.LoanEntity;
import com.example.library.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan")
@Controller
public class loanControlller {
    private LoanService loanService;

    public loanControlller(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loans/creat/{memberId}/{bookId}")
    public LoanEntity createLoan(@PathVariable Long memberId, @PathVariable Long bookId) {
        return loanService.createLoan(memberId, bookId);
    }

    @PostMapping("/loans/complate/{Id}")
    public LoanEntity complateLoan(@PathVariable Long Id) {
        return loanService.completeLoan(Id);
    }

    @GetMapping("/members/loans/{memberId}")
    public String listMemberLoans(@PathVariable Long memberId) {
        return loanService.listMemberLoans(memberId);
    }

}
