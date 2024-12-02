package com.jwt.study.tokendemo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.study.tokendemo.domain.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity,Long>{
    public Optional<MemberEntity>findByUsername(String username) ;

    
} 
