package com.boolsazo.bankchall.service;


import com.boolsazo.bankchall.dto.AccountTransferRequestDto;
import com.boolsazo.bankchall.dto.AccountTransferResponseDto;
import com.boolsazo.bankchall.dto.AuthorizationRequestDto;
import com.boolsazo.bankchall.dto.AuthorizationResponseDto;
import com.boolsazo.bankchall.dto.RequestTokenDto;
import com.boolsazo.bankchall.dto.ResponseTokenDto;
import com.boolsazo.bankchall.dto.UserAccountListRequestDto;
import com.boolsazo.bankchall.dto.UserAccountListResponseDto;

public interface OpenBankClient {

    // 사용자 인증 api
    public AuthorizationResponseDto oAuth(int user_id, AuthorizationRequestDto authorizationRequestDto) throws Exception;

    // 토큰 발급 api
    public ResponseTokenDto requestToken(int user_id, RequestTokenDto tokenRequestDto) throws Exception;

    // 사용자 정보 조회 api
    public UserAccountListResponseDto requestUserList(UserAccountListRequestDto userAccountListRequestDto) throws Exception;

    // 출금 이체 api & 입금 이체 api
    public AccountTransferResponseDto requestTransfer(AccountTransferRequestDto accountTransferRequestDto) throws Exception;
}
