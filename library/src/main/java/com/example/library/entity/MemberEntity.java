package com.example.library.entity;

import com.example.library.enums.MemberEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.Objects;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "member_entity")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = PRIVATE)
public class MemberEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String fullName;
    String email;
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    MemberEnum memberStatus;


  
}
