package com.leon.ms_accounts.service.accountMovement.impl;

import com.leon.ms_accounts.domain.Account;
import com.leon.ms_accounts.domain.AccountMovement;
import com.leon.ms_accounts.exception.EntityNotFoundException;
import com.leon.ms_accounts.exception.InsufficientBalanceException;
import com.leon.ms_accounts.repository.AccountMovementRepository;
import com.leon.ms_accounts.repository.AccountRepository;
import com.leon.ms_accounts.service.accountMovement.AccountMovementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountMovementServiceImpl implements AccountMovementService {
    private final AccountMovementRepository accountMovementRepository;
    private final AccountRepository accountRepository;

    @Override
    public AccountMovement saveAccountMovement(AccountMovement accountMovement) throws InsufficientBalanceException {
        Account account = accountRepository.findById(accountMovement.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        BigDecimal newBalance = account.getInitialBalance().add(accountMovement.getAmount());
        if (newBalance.doubleValue() < 0.0) {
            throw new InsufficientBalanceException("Balance not available");
        }
        account.setInitialBalance(newBalance);
        accountRepository.save(account);
        accountMovement.setBalance(newBalance);
        return accountMovementRepository.save(accountMovement);
    }

    @Override
    public Page<AccountMovement> getAllAccountMovements(Pageable pageable) {
        return accountMovementRepository.findAll(pageable);
    }

    @Override
    public Optional<AccountMovement> getAccountMovementById(Long id) {
        return accountMovementRepository.findById(id);
    }

    @Override
    public AccountMovement updateAccountMovement(Long id, AccountMovement accountMovementDetails) {
        Optional<AccountMovement> existingAccountMovementOptional = getAccountMovementById(id);
        if(existingAccountMovementOptional.isEmpty()) {
            throw new EntityNotFoundException("Account movement not found with id " + id);
        }

        AccountMovement existingAccountMovement = existingAccountMovementOptional.get();
        existingAccountMovement.setMovementType(accountMovementDetails.getMovementType());
        existingAccountMovement.setMovementDate(accountMovementDetails.getMovementDate());
        return accountMovementRepository.save(existingAccountMovement);
    }

    @Override
    public void deleteAccountMovement(Long id) {
        accountMovementRepository.deleteById(id);
    }

    @Override
    public List<AccountMovement> getAllMovementsByAccountIdInDates(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        return accountMovementRepository.findByAccountIdAndMovementDateBetween(accountId,startDate,endDate);
    }
}
