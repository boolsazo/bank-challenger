package com.boolsazo.bankchall.repository;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SavingHistoryRepositoryTest {

    @Autowired
    SavingHistoryRepository savingHistoryRepository;

    @Test
    @DisplayName("goal_id 일치하는 SavingHistory(입금내역 테이블)의 행 삭제하기")
    public void deleteAllByGoalId() {
        savingHistoryRepository.deleteByGoalId(1);
    }


}