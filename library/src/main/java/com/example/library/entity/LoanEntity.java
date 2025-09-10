package com.example.library.entity;

import com.example.library.enums.BookEnum;
import com.example.library.enums.LoanEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "loan_entity")
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private Long memberId;
    private LocalDateTime loanDate=LocalDateTime.now();
    private LocalDateTime returnDate=LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private LoanEnum loanStatus;


}
