package com.boolsazo.bankchall.repository;


import com.boolsazo.bankchall.domain.Goal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

}