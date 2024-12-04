package com.jwt.study.tokendemo.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.jwt.study.tokendemo.domain.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequestDTO {
    private String nickname;
    private String password;
    private String phone;
    private String profile_img;
    private String username;
    private List<String> roles = new ArrayList<>();

    public MemberEntity toEntity(String encondedPassword, List<String> roles) {
        return MemberEntity.builder()
            .username(username)
            .password(encondedPassword)
            .nickname(nickname)
            .phone(phone)
            .profile_img(profile_img)
            .username(username)
            .roles(roles)
            .build();
    }
}


