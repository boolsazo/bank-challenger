package com.boolsazo.bankchall.service;

import com.boolsazo.bankchall.domain.GoalAccount;
import com.boolsazo.bankchall.dto.GoalAccountNativeQueryResponse;

public interface GoalAccountService {

    void registerGoalAccount(GoalAccount vo) throws Exception;

    GoalAccountNativeQueryResponse showGoalWAccount(int goalId) throws Exception;

    GoalAccountNativeQueryResponse showGoalSAccount(int goalId) throws Exception;

    void deleteByGoalId(int goalId) throws Exception;

}
