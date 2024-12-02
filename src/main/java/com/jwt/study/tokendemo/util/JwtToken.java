package com.jwt.study.tokendemo.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// openssl rand -hex 32 암호화기 생성하고 설정 등록
@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    private String grantType; // jwt 인증 타입으로 Bearer 방식 사용
    private String accessToken;
    private String refreshToken;
}