package com.jwt.study.tokendemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.study.tokendemo.dao.MemberRepository;
import com.jwt.study.tokendemo.domain.MemberEntity;
import com.jwt.study.tokendemo.domain.dto.MemberResponseDTO;
import com.jwt.study.tokendemo.domain.dto.SignUpRequestDTO;
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
    // 가입 시 패스워드 암호화
    private final PasswordEncoder passwordEncoder;

    public Optional<MemberEntity> loginChk(SigninRequestDTO params) {

        System.out.println("debug >>>> service loginChk");

        return memberRepository.findByUsername(params.getUsername());

    }


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


    /*
    1. 중복가입 방지를 위해서 먼저 사용자 이름이 존재하는 지 여부 판단
    2. 패스워드 암호화 PasswordEncoder를 이용
    3. 권한부여 ("User")
    4. 매개변수로 들어온 객체를 MemberEntity 변환하여 레포지토리의 save() 메서드를 이용해 가입 완료
     */
    @Transactional
    public MemberResponseDTO signUp(SignUpRequestDTO params) {
        System.out.println("debug >>> service signUp ");
        String encodedPassword = passwordEncoder.encode(params.getPassword());
        System.out.println("debug >>> encodedPassword : " + encodedPassword);
        System.err.println("debug >>> before roles :" + params.getRoles());
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        System.err.println("debug >>> after roles :" + roles);

        MemberEntity entity = params.toEntity(encodedPassword, roles);
        return MemberResponseDTO.toDto(memberRepository.save(entity));
    }

}
