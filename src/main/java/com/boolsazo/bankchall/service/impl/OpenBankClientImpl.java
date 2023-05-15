package com.boolsazo.bankchall.service.impl;

import com.boolsazo.bankchall.dto.AccountTransferRequestDto;
import com.boolsazo.bankchall.dto.AccountTransferResponseDto;
import com.boolsazo.bankchall.dto.AuthorizationRequestDto;
import com.boolsazo.bankchall.dto.AuthorizationResponseDto;
import com.boolsazo.bankchall.dto.RequestTokenDto;
import com.boolsazo.bankchall.dto.ResponseTokenDto;
import com.boolsazo.bankchall.dto.UserAccountListRequestDto;
import com.boolsazo.bankchall.dto.UserAccountListResponseDto;
import com.boolsazo.bankchall.service.OpenBankClient;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.MultiValueMap;

@Service
public class OpenBankClientImpl implements OpenBankClient {

    private OpenBankUtil openBankUtil;
    private HttpHeaders httpHeaders;
    private RestTemplate restTemplate;

    @Value("${openbank.bank_tran_id}")
    private String useCode; // 핀테크번호+U -> 거래고유번호 생성기

    @Value("${openbank.client_id}")
    private String clientId;

    @Value("${openbank.client_secret}")
    private String client_secret;
    private String redirect_uri = "http://localhost:8080/";
    private String base_url = "https://testapi.openbanking.or.kr/v2.0";

    public OpenBankClientImpl() {
    }

    // 사용자 인증 api
    @Override
    public AuthorizationResponseDto oAuth(int user_id,
        AuthorizationRequestDto authorizationRequestDto) throws Exception {
       return null;
    }

    @Override
    public ResponseTokenDto requestToken(int user_id, RequestTokenDto tokenRequestDto)
        throws Exception {
        //post
        //http 헤더 오브젝트 생성
//        httpHeaders.add("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//        //httpBody 오브젝트 생성
//        tokenRequestDto.setRequestToken(clientId,client_secret,redirect_uri,"authorization_code");
//        tokenRequestDto.setRequestToken(clientId,);
//        tokenRequestDto.setBankRequestToken(clientId,client_secret,redirect_uri,"authorization_code");
//        //헤더의 컨텐트 타입이 application/x-www-form-urlencoded;charset=UTF-8이므로 객체를 집어넣을수 없음.. 그러므로 MultiValueMap 사용 해야함
//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("code",tokenRequestDto.getCode());
//        parameters.add("client_id",tokenRequestDto.getClient_id());
//        parameters.add("client_secret",tokenRequestDto.getClient_secret());
//        parameters.add("redirect_uri",tokenRequestDto.getRedirect_uri());
//        parameters.add("grant_type",tokenRequestDto.getGrant_type());
//        // HttpHeader 와 HttpBody를 하나의 오브젝트에 담기
//        HttpEntity<MultiValueMap<String,String>> param =
//            new HttpEntity<>(parameters,httpHeaders);
//        //Http 요청하기 - post 방식으로
//        return restTemplate.exchange("https://testapi.openbanking.or.kr/oauth/2.0/token", HttpMethod.POST, param, BankReponseToken.class).getBody();
        return null;
    }

    @Override
    public UserAccountListResponseDto requestUserList(
        UserAccountListRequestDto userAccountListRequestDto) throws Exception {
        return null;
    }

    @Override
    public AccountTransferResponseDto requestTransfer(
        AccountTransferRequestDto accountTransferRequestDto) throws Exception {
        return null;
    }
}
