package com.leon.ms_accounts.controller;

import com.leon.ms_accounts.domain.dto.response.AccountReportDTO;
import com.leon.ms_accounts.service.report.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reportes")
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<AccountReportDTO>> obtenerReporte(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate,
            @RequestParam("clientId") Long clientId) {

        List<AccountReportDTO> report = reportService.accountReportByClientInDates(clientId, startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
