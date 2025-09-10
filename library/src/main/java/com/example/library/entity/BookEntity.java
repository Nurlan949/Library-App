package com.example.library.entity;

import com.example.library.enums.BookEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "book_entity")
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @JoinColumn(unique = true)
    private String isbn;
    @Enumerated(EnumType.STRING)
    private BookEnum bookStatus;

}
