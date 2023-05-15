package com.boolsazo.bankchall.service.impl;

import com.boolsazo.bankchall.domain.GoalAccount;
import com.boolsazo.bankchall.dto.GoalAccountNativeQueryResponse;
import com.boolsazo.bankchall.repository.GoalAccountRepository;
import com.boolsazo.bankchall.service.GoalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalAccountServiceImpl implements GoalAccountService {

    @Autowired
    private GoalAccountRepository repository;

    @Override
    public void registerGoalAccount(GoalAccount vo) throws Exception {
        repository.save(vo);
    }

    @Override
    public GoalAccountNativeQueryResponse showGoalWAccount(int goalId) throws Exception {
        return repository.showGoalWAccount(goalId);
    }

    @Override
    public GoalAccountNativeQueryResponse showGoalSAccount(int goalId) throws Exception {
        return repository.showGoalSAccount(goalId);
    }

    @Override
    public void deleteByGoalId(int goalId) throws Exception {
        repository.deleteByGoalId(goalId);
    }
}
