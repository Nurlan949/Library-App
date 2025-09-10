package com.example.library.service;

import com.example.library.entity.BookEntity;
import com.example.library.entity.LoanEntity;
import com.example.library.enums.LoanEnum;
import com.example.library.exception.MemberIsNotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;


    public LoanService(BookRepository bookRepository, MemberRepository memberRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    public LoanEntity createLoan(Long memberId, Long bookId) {
        LoanEntity loan = new LoanEntity();
        loan.setBookId(bookId);
        loan.setMemberId(memberId);
        loanRepository.save(loan);
        return loan;
    }

    public LoanEntity completeLoan(Long Id) {
        LoanEntity loan = loanRepository.findById(Id).orElseThrow(() -> new RuntimeException("Borc yoxdur"));
        loan.setLoanStatus(LoanEnum.RETURNED);
        loanRepository.save(loan);
        return loan;
    }



    public String listMemberLoans(Long memberId) {
        List<LoanEntity> allLoan = loanRepository.findAll();
        for (LoanEntity loan : allLoan) {
            if (loan.getMemberId().equals(memberId)) {
                return loan.toString();
            }


        }
        return  "uzv tapilmaidi";
    }




}
