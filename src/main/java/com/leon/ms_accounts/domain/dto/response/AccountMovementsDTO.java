package com.leon.ms_accounts.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class AccountMovementsDTO {
    private LocalDateTime movementDate;
    private String movementType;
    private BigDecimal amount;
    private BigDecimal balance;
}
