package com.boolsazo.bankchall.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "saving_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SavingHistoryPK.class)
public class SavingHistory {
    @Id
    @Column(name = "account_id", nullable = false)
    private int accountId;

    @Id
    @Column(name = "goal_id", nullable = false)
    private int goalId;

    @Id
    @Column(name = "user_id" , nullable = false)
    private int userId;

    @Column(name = "save_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime saveDate;
}
