package com.leon.ms_accounts.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AccountMovementEvent {
    private Long accountId;
    private Long clientId;
    private BigDecimal newBalance;
}
