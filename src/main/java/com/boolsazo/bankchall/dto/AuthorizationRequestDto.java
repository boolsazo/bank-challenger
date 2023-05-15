package com.boolsazo.bankchall.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
public class AuthorizationRequestDto {

    private String response_type = "code";

    @Value("${openbank.client_id}")
    private String client_id;

    private String redirect_uri = "http://localhost:8080/";
    private String scope = "login inquiry transfer";
    private String state;   // 보안 위협에 대응하기 위해 이용기관(우리)이 세팅하는 난수값
    private String auth_type = "0";


}
