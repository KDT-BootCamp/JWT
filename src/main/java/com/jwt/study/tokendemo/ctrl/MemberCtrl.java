package com.jwt.study.tokendemo.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.study.tokendemo.config.SecurityUtil;
import com.jwt.study.tokendemo.domain.dto.SigninRequestDTO;
import com.jwt.study.tokendemo.service.MemberService;
import com.jwt.study.tokendemo.util.JwtToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberCtrl {

    private final MemberService memberService;

    @PostMapping("/sign-in")
    public JwtToken postMethodName(@RequestBody SigninRequestDTO params) {
        System.out.println("debug >>> ctrl endpoint /members/sign-in");
        log.info("request params username = {}, password = {}", params.getUsername(), params.getPassword());
        // System.out.println("debug >>> params = " + params );
        JwtToken token = memberService.signIn(params);
        System.out.println("debug >>> type = " + token.getGrantType());
        System.out.println("debug >>> type = " + token.getAccessToken());
        System.out.println("debug >>> type = " + token.getRefreshToken());
        return token;
    }

    @PostMapping("/test")
    public String test(@RequestBody String entity) {
       System.out.println("debug >>> ctrl endpoin /members/test");
        
        return SecurityUtil.getCurrentUser();
    }
    


}
