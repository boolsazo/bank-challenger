package com.boolsazo.bankchall.service;

import com.boolsazo.bankchall.dto.AccountResponse;
import com.boolsazo.bankchall.dto.RegistAccountRequest;

public interface AccountService {

    void registWithdrawAccount(RegistAccountRequest request);

    void registSavingAccount(RegistAccountRequest request);

    AccountResponse withdrawList(int userId);

    AccountResponse savingsList(int userId);

    void deleteAccount(int accountId);
}
