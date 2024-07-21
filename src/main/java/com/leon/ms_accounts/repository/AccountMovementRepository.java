package com.leon.ms_accounts.repository;

import com.leon.ms_accounts.domain.AccountMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccountMovementRepository extends JpaRepository<AccountMovement, Long> {
    List<AccountMovement> findByAccountIdAndMovementDateBetween(Long accountId,
                                                                LocalDateTime startDate, LocalDateTime endDate);
}
