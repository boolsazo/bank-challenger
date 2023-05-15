package com.boolsazo.bankchall.dto;

import lombok.Data;

@Data
public class AuthorizationResponseDto {
    private String code;
    private String scope;
    private String state;
}
