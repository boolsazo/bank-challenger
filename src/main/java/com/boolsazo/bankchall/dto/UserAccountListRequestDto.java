package com.boolsazo.bankchall.dto;

import lombok.Data;

@Data
public class UserAccountListRequestDto {
    private String access_token;
    private String user_seq_no;
}
