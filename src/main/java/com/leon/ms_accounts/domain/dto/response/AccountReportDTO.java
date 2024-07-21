package com.leon.ms_accounts.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class AccountReportDTO {
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private boolean status;
    private List<AccountMovementsDTO> movements;
}
