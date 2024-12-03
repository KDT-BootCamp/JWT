package com.jwt.study.tokendemo.domain.dto;

import lombok.Data;

@Data
public class SigninRequestDTO {
 
    private String username; 
    private String password; 
}