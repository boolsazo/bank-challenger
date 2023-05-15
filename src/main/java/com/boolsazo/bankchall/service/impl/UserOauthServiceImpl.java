package com.boolsazo.bankchall.service.impl;

import com.boolsazo.bankchall.domain.UserOauth;
import com.boolsazo.bankchall.repository.UserOauthRepository;
import com.boolsazo.bankchall.service.UserOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserOauthServiceImpl implements UserOauthService {

    @Autowired
    private UserOauthRepository repository;
    @Override
    public void registerUserOauth(UserOauth vo) throws Exception {
        repository.save(vo);
    }

    @Override
    public UserOauth showUserOauth(int userId) throws Exception {
        return repository.findByUserId(userId);
    }

    @Override
    public void deleteUserOauth(int userId) throws Exception {
        repository.deleteByUserId(userId);
    }

    public boolean existsByUserId(int userId) throws Exception {
        return repository.existsByUserId(userId);
    }
}
