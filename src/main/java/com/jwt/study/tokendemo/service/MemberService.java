package com.jwt.study.tokendemo.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.study.tokendemo.dao.MemberRepository;
import com.jwt.study.tokendemo.domain.dto.SigninRequestDTO;
import com.jwt.study.tokendemo.util.JwtToken;
import com.jwt.study.tokendemo.util.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    //Spring Security 인증 구성을 설정하는데 사용되는 유틸리티
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @Transactional
    public JwtToken signIn(SigninRequestDTO params) {
        System.out.println("debug >>> service signIn ");
    
        /*
         * 1. 사용자 아이디, 패스워드 기반 인증 진행(Authentication 객체 생성)
         * 2. 검증 
         * 3. 토큰 생성
         */
        
        //로그인
        UsernamePasswordAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(params.getUsername(), params.getPassword());

        System.out.println("debug >>> UserPasswordAuthenticationToekn : " + authenticationToken);
    
        // 검증, authenticate()가 실행될 때 CustomerUserDetailService 메서드를 콜백하게 됨
        Authentication authentication = 
            authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        return jwtToken;
    }

}
