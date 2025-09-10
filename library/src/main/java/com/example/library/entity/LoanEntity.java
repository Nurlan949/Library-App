package com.example.library.entity;

import com.example.library.enums.BookEnum;
import com.example.library.enums.LoanEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "loan_entity")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class LoanEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long bookId;
    Long memberId;
    LocalDateTime loanDate=LocalDateTime.now();
    LocalDateTime returnDate=LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    LoanEnum loanStatus;


}
