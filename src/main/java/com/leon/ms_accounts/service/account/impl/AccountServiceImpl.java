package com.leon.ms_accounts.service.account.impl;

import com.leon.ms_accounts.domain.Account;
import com.leon.ms_accounts.exception.EntityNotFoundException;
import com.leon.ms_accounts.repository.AccountRepository;
import com.leon.ms_accounts.service.account.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account updateAccount(Long id, Account updatedAccount) {
        Optional<Account> existingAccountOptional = getAccountById(id);
        if(existingAccountOptional.isEmpty()) {
            throw new EntityNotFoundException("Account not found with id " + id);
        }

        Account existingAccount = existingAccountOptional.get();
        existingAccount.setAccountType(updatedAccount.getAccountType());
        existingAccount.setStatus(updatedAccount.isStatus());
        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> getAllAccountsByClientId(Long clientId) {
        return accountRepository.findAllByClientId(clientId);
    }
}
