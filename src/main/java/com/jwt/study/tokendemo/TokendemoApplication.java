package com.jwt.study.tokendemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * 사용자 인증 방식 : 세션, 토큰 기반
 * Spring Security + JWT  : 토큰 기반
 */


@SpringBootApplication
public class TokendemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokendemoApplication.class, args);
	}

}
