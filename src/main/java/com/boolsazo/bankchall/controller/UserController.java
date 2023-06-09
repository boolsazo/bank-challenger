package com.boolsazo.bankchall.controller;

import com.boolsazo.bankchall.naver.NaverLoginApi;
import com.boolsazo.bankchall.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@Tag(name = "사용자", description = "사용자 API")
public class UserController {

    NaverLoginApi naverLoginApi = NaverLoginApi.getInstance();
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    @Operation(summary = "네이버 로그인 API", description = "네이버 OPEN API를 이용하여 로그인 할 수 있는 API")
    public String goNaverLogin() {
        String clientId = naverLoginApi.getClientId();
        String callbackUrl = naverLoginApi.getCallbackUrl();
        String state = naverLoginApi.getState();

        String url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" +
                clientId + "&redirect_uri=" + callbackUrl + "&state=" + state;

        return "redirect:" + url;
    }


    @GetMapping("/login/callback")
    @Operation(summary = "네이버 로그인 callback API", description = "네이버 OPEN API를 이용하여 로그인 할 수 있는 API")
    public String LoginProcess(HttpServletRequest request) {
        // cancel
        if (request.getParameter("error") != null) {
            return "redirect:/";
        }

        /******************************************************************************************
         * 1. GET ACCESS TOKEN                                                                    *
         ******************************************************************************************/

        String clientId = naverLoginApi.getClientId();
        String clientSecret = naverLoginApi.getClientSecret();
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        String tokenUrl =
                "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id=" +
                        clientId + "&client_secret=" + clientSecret + "&code=" + code + "&state=" + state;

        // Access token by post
        HttpEntity<String> entity = new HttpEntity<>("", new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, entity,
                String.class);

        // Parsing response.body from string to json object
        Gson gson = new Gson();
        JsonObject tokenObject = gson.fromJson(response.getBody(), JsonObject.class);
        String access_token = tokenObject.get("access_token").getAsString();

        /******************************************************************************************
         * 2. GET NAVER PROFILE                                                                   *
         ******************************************************************************************/

        // Get profile by get
        String header = "Bearer " + access_token;
        String profileUrl = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);

        String responseBody = naverLoginApi.get(profileUrl, requestHeaders);
        // response 전체를 parsing
        JsonObject profileObject = gson.fromJson(responseBody, JsonObject.class);
        // response.response 정보만 parsing
        JsonObject responseData = profileObject.get("response").getAsJsonObject();

        String id = responseData.get("id").getAsString();
        String name = responseData.get("name").getAsString();
        String age = responseData.get("age").getAsString();
        String gender = responseData.get("gender").getAsString();
        String email = responseData.get("email").getAsString();
        String birthyear = responseData.get("birthyear").getAsString();
        String profileImage = responseData.get("profile_image").getAsString();

        /******************************************************************************************
         * 3. DO LOGIN OR REGISTER                                                                *
         ******************************************************************************************/

        HttpSession session = request.getSession();
        session.setAttribute("sessionId", naverLoginApi.generateRandomState());

        System.out.println("id(토큰): " + id);
        System.out.println("이름: " + name);
        System.out.println("연령대: " + age);
        System.out.println("성별: " + gender);
        System.out.println("이메일: " + email);
        System.out.println("출생년도: " + birthyear);
        System.out.println("프로필이미지링크: " + profileImage);
        System.out.println(session.getAttribute("sessionId"));

        String redirectUrl = "redirect:/session";

        int userId = 0;
        try {
            // Register
            if (!userService.existsByEmail(email)) {
                System.out.println("** Register");
                try {
                    userService.registerUser(id, name, age, gender, email, birthyear, profileImage);
                } catch (Exception e2) {
                    System.out.println("ERROR IN REGISTER");
                }
            }

            System.out.println("** Login");
            userId = userService.findUserIdByEmail(email);
        } catch (Exception e) {
            System.out.println("ERROR IN FIND USER");
        } finally {
            session.setAttribute("userId", userId);
            System.out.println(email + " user login.");
        }

        System.out.println(session.getAttribute("userId"));
        return redirectUrl;
    }

    @GetMapping("/logout")
    @Operation(summary = "로그아웃 API", description = "로그인한 사용자의 세션을 삭제할 수 있는 API")
    public String logout(HttpSession session) {
        if (session.getAttribute("sessionId") != null) {
            session.invalidate();
            System.out.println("세션 제거");
        }
        System.out.println("로그아웃");
        return "redirect:/";
    }

    @DeleteMapping(value = "/delete")
    @Operation(summary = "회원 탈퇴 API", description = "해당 사용자가 로그인된 사용자인지 확인할 수 있는 API")
    public String delete(HttpServletRequest request) {
        try {
            int userId = (int) request.getSession().getAttribute("userId");
            userService.deleteByUserId(userId);
            System.out.println("회원 탈퇴");
        } catch (Exception e) {
            System.out.println("Error in delete user");
        }
        request.getSession().invalidate();
        System.out.println("세션 제거");

        return "redirect:/";
    }

}
