package com.boolsazo.bankchall.service;

import com.boolsazo.bankchall.domain.UserOauth;

public interface UserOauthService {

    void registerUserOauth(UserOauth vo) throws Exception;

    UserOauth showUserOauth(int userId) throws Exception;

    void deleteUserOauth(int userId) throws Exception;

    boolean existsByUserId(int userId) throws Exception;
}
