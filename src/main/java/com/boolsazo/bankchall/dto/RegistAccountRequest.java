package com.boolsazo.bankchall.dto;


import com.boolsazo.bankchall.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistAccountRequest {

    private String accountNumMasked;
    private String bankName;
    private boolean isUsed = true;
    private int userId;

    public Account toWithdrawEntity() {
        return new Account(userId,
                accountNumMasked,
                bankName,
                isUsed,
                0);
    }

    public Account toSavingsEntity() {
        return new Account(userId,
                accountNumMasked,
                bankName,
                isUsed,
                1);
    }
}
