package com.example.library.repository;

import com.example.library.entity.LoanEntity;
import com.example.library.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
