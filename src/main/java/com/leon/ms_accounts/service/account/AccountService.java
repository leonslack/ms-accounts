package com.leon.ms_accounts.service.account;

import com.leon.ms_accounts.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface AccountService {
    Account saveAccount(Account account);
    Page<Account> getAllAccounts(Pageable pageable);
    Optional<Account> getAccountById(Long id) ;
    Account updateAccount(Long id, Account updatedAccount) ;
    void deleteAccount(Long id);
    List<Account> getAllAccountsByClientId(Long clientId);
}
