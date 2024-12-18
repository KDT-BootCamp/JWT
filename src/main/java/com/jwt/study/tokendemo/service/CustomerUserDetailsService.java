package com.jwt.study.tokendemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.study.tokendemo.dao.MemberRepository;
import com.jwt.study.tokendemo.domain.MemberEntity;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// Spring Security 에서 사용자 데이터가 존재한다면 UserDetails 객체로 반환
public class CustomerUserDetailsService implements UserDetailsService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("debug >>> CustomerUserDetailsService username : " + username);
        UserDetails result = memberRepository.findByUsername(username)
            .map(this::createUserDetails)
            .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        
        System.out.println("debug >>> CustomerUserDetailsService MemberEntity = " + result );
        return result;
    }

    // 해당 user의 데이터가 존재하면 Userdetails 객체를 반환
    private UserDetails createUserDetails(MemberEntity member){
        return User.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }

}
