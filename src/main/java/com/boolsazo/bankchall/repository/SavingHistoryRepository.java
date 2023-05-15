package com.boolsazo.bankchall.repository;

import com.boolsazo.bankchall.domain.SavingHistory;
import com.boolsazo.bankchall.domain.SavingHistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingHistoryRepository extends JpaRepository<SavingHistory, SavingHistoryPK> {

    @Modifying
    @Query(value = "DELETE FROM saving_history WHERE goal_id = :goalId ", nativeQuery = true)
    void deleteByGoalId(@Param("goalId") int goalId);

}
