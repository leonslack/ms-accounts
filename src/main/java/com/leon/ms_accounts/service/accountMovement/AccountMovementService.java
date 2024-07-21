package com.leon.ms_accounts.service.accountMovement;

import com.leon.ms_accounts.domain.AccountMovement;
import com.leon.ms_accounts.exception.InsufficientBalanceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountMovementService {
    AccountMovement saveAccountMovement(AccountMovement accountMovement) throws InsufficientBalanceException;
    Page<AccountMovement> getAllAccountMovements(Pageable pageable);
    Optional<AccountMovement> getAccountMovementById(Long id);
    AccountMovement updateAccountMovement(Long id, AccountMovement updatedAccountMovement);
    void deleteAccountMovement(Long id);
    List<AccountMovement> getAllMovementsByAccountIdInDates(Long accountId,
                                                            LocalDateTime startDate, LocalDateTime endDate);

}
