package com.example.library.entity;

import com.example.library.enums.BookEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Entity
@Data
@Table(name = "book_entity")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String title;
     String author;
    @JoinColumn(unique = true)
     String isbn;
    @Enumerated(EnumType.STRING)
     BookEnum bookStatus;

}
