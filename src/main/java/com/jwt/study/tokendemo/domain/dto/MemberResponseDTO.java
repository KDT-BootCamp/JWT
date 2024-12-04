package com.jwt.study.tokendemo.domain.dto;

import com.jwt.study.tokendemo.domain.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class MemberResponseDTO {

    private Long id;
    private String nickname;
    private String password;
    private String phone;
    private String profile_img;
    private String username;

    public static MemberResponseDTO toDto(MemberEntity member){
        return MemberResponseDTO.builder()
        .id(member.getId())
        .password(member.getPassword())
        .username(member.getUsername())
        .nickname(member.getNickname())
        .phone(member.getPhone())
        .profile_img(member.getProfile_img())
        .build()
        ;
    }
}
