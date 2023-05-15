package com.boolsazo.bankchall.controller;

import com.boolsazo.bankchall.domain.UserOauth;
import com.boolsazo.bankchall.dto.api.ResponseTokenDto;
import com.boolsazo.bankchall.dto.api.UserAccountListResponseDto;
import com.boolsazo.bankchall.service.impl.OpenBankClientImpl;
import com.boolsazo.bankchall.service.impl.UserOauthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openapi")
public class OpenBankAPIController {
    @Autowired
    private OpenBankClientImpl openBankClient;
    private UserOauthServiceImpl userOauthService;
    @GetMapping("/{userId}/{code}")
    public ResponseEntity registerAccessToken(@PathVariable("userId") int userId, @PathVariable("code") String code) {
        try {
            // if) 사용자 인증 테이블에 userId가 있으면, 바로 사용자 조회 api
            // else) 토큰 발급api -> 사용자 조회 api
            // 테이블 조회
            if(!userOauthService.existsByUserId(userId)) {
                // 토큰 발급 api
                ResponseTokenDto tokenResponse = openBankClient.requestToken(userId, code);
                System.out.println(tokenResponse);
                // 2.access_token, seq 저장
            }
//            System.out.println("----------------------------------");
//            UserOauth vo = new UserOauth(userId, tokenResponse.getUser_seq_no(), tokenResponse.getAccess_token());
//            System.out.println(vo);
//            userOauthService.registerUserOauth(vo);
            // 3. 사용자 조회 api
            UserAccountListResponseDto userAccountListResponse = openBankClient.requestUserList("1101032212","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAxMDMyMjEyIiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2OTE5MjM2NTMsImp0aSI6IjIwM2FjODY5LTk5ZmQtNDAwZC1iNTY4LTVmYzcyOWQ2NTZiOSJ9.ZSanrjHunV2mNFPvs4qZFtjEemAb3XHec3I-5HYtBb4");
            System.out.println(userAccountListResponse);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to register AccessToken.");
        }
        return null;
    }

//    public ResponseEntity
    // 1. userId 받아서
    // 2. DB Bearer Token 헤더에 놓고
    // exchange

}
