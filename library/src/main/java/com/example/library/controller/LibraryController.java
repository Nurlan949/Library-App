package com.example.library.controller;

import com.example.library.entity.BookEntity;
import com.example.library.entity.LoanEntity;
import com.example.library.entity.MemberEntity;
import com.example.library.service.LibraryService;
import com.example.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@Controller
public class LibraryController {
    private LibraryService libraryService;


    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    @PostMapping("/books")
    public BookEntity addBook(@RequestBody BookEntity book){
       return libraryService.addBook(book.getTitle(),book.getAuthor(),book.getIsbn());

    }
    @PostMapping("/members")
    public MemberEntity registerMember(@RequestBody MemberEntity member){
        return libraryService.registerMember(member.getFullName(),member.getEmail(),member.getPhoneNumber());

    }
    @PostMapping("/loans/borrow")
    public LoanEntity borrowBook(@RequestBody LoanEntity loan){
       return libraryService.borrowBook(loan.getMemberId(), loan.getBookId());

    }
    @PostMapping("/loans/return/{id}")
    public LoanEntity returnBook(@PathVariable Long id,@RequestBody LoanEntity loan){
       return libraryService.returnBook(id,loan);
    }
    @GetMapping("/books/available")
    public List<BookEntity>  listAvailableBooks(){
        return libraryService.listAvailableBooks();
    }
    @GetMapping("/books/borrow/{memberId}/{bookId}")
    public LoanEntity listBorrowedBooks(@PathVariable Long memberId, @PathVariable Long bookId){
        return libraryService.borrowBook(memberId,bookId);
    }
    @GetMapping("/members/{memberId}")
    public MemberEntity listMemberLoans(@PathVariable Long memberId){
        return libraryService.listMemberLoans(memberId);
    }
}
