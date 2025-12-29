package com.springboot.shoppingcart.auth.dto;

import com.springboot.shoppingcart.auth.jwt.Jwt;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private Jwt accessToken;
    private Jwt refreshToken;
}
