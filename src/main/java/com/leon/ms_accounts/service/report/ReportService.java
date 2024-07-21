package com.leon.ms_accounts.service.report;

import com.leon.ms_accounts.domain.dto.response.AccountReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<AccountReportDTO> accountReportByClientInDates(Long clientId, LocalDateTime startDate, LocalDateTime endDate);
}
