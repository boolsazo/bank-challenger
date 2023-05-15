package com.boolsazo.bankchall.repository;

import com.boolsazo.bankchall.domain.User;
import com.boolsazo.bankchall.domain.UserOauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOauthRepository extends JpaRepository<UserOauth, Integer>  {
    boolean existsByUserId(int userId) throws Exception;
    UserOauth findByUserId(int userId) throws Exception;
    void deleteByUserId(int userId) throws Exception;
}
