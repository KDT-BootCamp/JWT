package com.jwt.study.tokendemo.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.study.tokendemo.config.SecurityUtil;
import com.jwt.study.tokendemo.domain.dto.SigninRequestDTO;
import com.jwt.study.tokendemo.domain.MemberEntity;
import com.jwt.study.tokendemo.domain.dto.MemberResponseDTO;
import com.jwt.study.tokendemo.domain.dto.SignUpRequestDTO;
import com.jwt.study.tokendemo.service.MemberService;
import com.jwt.study.tokendemo.util.JwtToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberCtrl {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/sign-in")
    public JwtToken postMethodName(@RequestBody SigninRequestDTO params) {
        System.out.println("debug >>> ctrl endpoint /members/sign-in");
        log.info("request params username = {}, password = {}", params.getUsername(), params.getPassword());
        // System.out.println("debug >>> params = " + params );

        Optional<MemberEntity> entity = memberService.loginChk(params);

        if( entity.get() != null ) {

            if( passwordEncoder.matches(params.getPassword(), entity.get().getPassword())) {

                System.out.println("debug >>>> ctrl password check ");

                params.setPassword(entity.get().getPassword());

                JwtToken token = memberService.signIn(params);

                System.out.println("debug >>>> type = "+token.getGrantType());

                System.out.println("debug >>>> access  token = "+token.getAccessToken());

                System.out.println("debug >>>> refresh token = "+token.getRefreshToken()); 

                return token ; 
            }
        }
        return null ;
    }

    @PostMapping("/test")
    public String test(@RequestBody String entity) {
       System.out.println("debug >>> ctrl endpoint /members/test");
        
        return SecurityUtil.getCurrentUser();
    }
    
    @PostMapping("/sign-up")
    public ResponseEntity<MemberResponseDTO> signUp(@RequestBody SignUpRequestDTO params) {
        System.out.println("debug >>> ctrl endpoint /members/sign-up");
        System.out.println("debug >> params = " + params);
        
        MemberResponseDTO memberResponseDTO = memberService.signUp(params);
        
        System.out.println("debug >>> MemberResponseDTO : " + memberResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDTO);
    }
}

// Client Secret
// M2bgdDkUtkVddiPoZEJKoKW1CirkSjcW

// Rest API
// 9f114d813d0b8085c64226c63a2b654b

// Redirect URL
// http://localhost:8087/login/oauth/kakao
