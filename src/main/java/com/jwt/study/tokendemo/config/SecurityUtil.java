package com.jwt.study.tokendemo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
 
    public static String getCurrentUser(){
        final Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("debug >>> SecurityUtil getCurrentUser authentication : " + authentication);
        if( authentication == null || authentication.getName() == null){
            throw new RuntimeException("사용자 정보를 찾을 수 없습니다.");
        }
        return authentication.getName();
    }
}
