package com.example.library.service;

import com.example.library.entity.BookEntity;
import com.example.library.entity.LoanEntity;
import com.example.library.entity.MemberEntity;
import com.example.library.enums.BookEnum;
import com.example.library.enums.LoanEnum;
import com.example.library.enums.MemberEnum;
import com.example.library.exception.MemberIsNotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/// 1 addBook(String title, String author, String isbn): Yeni kitab əlavə edir
/// 2 registerMember(String fullName, String email, String phoneNumber): Yeni üzv qeydiyyatdan keçir
/// 3 borrowBook(Long memberId, Long bookId): Üzv üçün kitabı borc olaraq qeydə alır
/// 4 returnBook(Long loanId): Borc götürülən kitabı geri qaytarır
/// 5 listAvailableBooks(): Hazırda mövcud olan (AVAILABLE) kitabları qaytarır
/// 6 listMemberLoans(Long memberId): Üzvün bütün borclarını qaytarır
@Service
public class LibraryService {
    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private LoanRepository loanRepository;


    public LibraryService(BookRepository bookRepository, MemberRepository memberRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    public BookEntity addBook(String title, String author, String isbn) {
        BookEntity books = new BookEntity();
        books.setTitle(title);
        books.setAuthor(author);
        books.setIsbn(isbn);

        books.setBookStatus(BookEnum.AVAILABLE);
        return bookRepository.save(books);
    }

    public MemberEntity registerMember(String fullName, String email, String phoneNumber) {
        MemberEntity members1 = new MemberEntity();
        members1.setFullName(fullName);
        members1.setEmail(email);
        members1.setPhoneNumber(phoneNumber);
        members1.setMemberStatus(MemberEnum.ACTIVE);
        return memberRepository.save(members1);
    }

    public LoanEntity borrowBook(Long memberId, Long bookId) {
        BookEntity book = new BookEntity();
        if (memberId == null || bookId == null) {
            throw new IllegalArgumentException("kitab yoxdur ve uzv movcud deyil!");
        }
        LoanEntity loans = new LoanEntity();
        loans.setBookId(bookId);
        loans.setMemberId(memberId);
        loans.setLoanStatus(LoanEnum.ACTIVE);
        loanRepository.save(loans);
        book.setBookStatus(BookEnum.BORROWED);
        bookRepository.save(book);
        return loans;
    }

    public LoanEntity returnBook(Long id, LoanEntity loan) {
        LoanEntity exitLoan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Borc tapilmadi"));

        if (!exitLoan.getBookId().equals(loan.getBookId())) {
            throw new IllegalArgumentException("Kitab Id-si uygun deyil");
        }


        if (exitLoan.getLoanStatus() == LoanEnum.RETURNED) {
            throw new IllegalArgumentException("Kitab qaytarildi");
        }
        loan.setLoanStatus(LoanEnum.RETURNED);
        exitLoan.setReturnDate(LocalDateTime.now());
        loanRepository.save(exitLoan);

        BookEntity book = bookRepository.findById(exitLoan.getBookId()).orElseThrow(() -> new RuntimeException("Kitab tapilmadi"));
        book.setBookStatus(BookEnum.AVAILABLE);
        bookRepository.save(book);
        loanRepository.delete(exitLoan);
        System.out.println("Kitab ugurla qaytarildi ve borc silindi");
        return exitLoan;
    }

    public List<BookEntity> listAvailableBooks() {
        List<BookEntity> bookList = new ArrayList<>();
        List<BookEntity> allBook = bookRepository.findAll();
        for (BookEntity book : allBook) {
            if (BookEnum.AVAILABLE.equals(book.getBookStatus())) {
                bookList.add(book);
            }
        }
        return bookList;
    }

    public MemberEntity listMemberLoans(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(() -> new MemberIsNotFoundException("Uzv tapilmadi!"));
        return member;
    }


}
