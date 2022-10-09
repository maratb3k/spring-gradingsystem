package com.example.gradingsystem.DTOs;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
