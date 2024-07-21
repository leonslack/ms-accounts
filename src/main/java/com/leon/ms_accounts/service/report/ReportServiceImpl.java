package com.leon.ms_accounts.service.report;

import com.leon.ms_accounts.domain.Account;
import com.leon.ms_accounts.domain.AccountMovement;
import com.leon.ms_accounts.domain.dto.response.AccountMovementsDTO;
import com.leon.ms_accounts.domain.dto.response.AccountReportDTO;
import com.leon.ms_accounts.service.account.AccountService;
import com.leon.ms_accounts.service.accountMovement.AccountMovementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final AccountService accountService;

    private final AccountMovementService accountMovementService;

    @Override
    public List<AccountReportDTO> accountReportByClientInDates(Long clientId,
                                                               LocalDateTime startDate, LocalDateTime endDate) {
        List<Account> accounts = accountService.getAllAccountsByClientId(clientId);
        List<AccountReportDTO> accountReportDTOS = new ArrayList<>();

        for (Account account : accounts) {
            List<AccountMovement> movements = accountMovementService
                    .getAllMovementsByAccountIdInDates(account.getId(), startDate, endDate);
            List<AccountMovementsDTO> movimientoDTOs = movements.stream()
                    .map(m -> new AccountMovementsDTO(m.getMovementDate(),
                                                      m.getMovementType(), m.getAmount(), m.getBalance()))
                    .collect(Collectors.toList());

            AccountReportDTO reportDTO = new AccountReportDTO(account.getAccountNumber(),
                                                              account.getAccountType(),
                                                              account.getInitialBalance(),
                                                              account.isStatus(), movimientoDTOs);
            accountReportDTOS.add(reportDTO);
        }

        return accountReportDTOS;
    }
}
