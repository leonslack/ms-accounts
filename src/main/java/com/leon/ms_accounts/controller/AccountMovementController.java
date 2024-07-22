package com.leon.ms_accounts.controller;

import com.leon.ms_accounts.domain.AccountMovement;
import com.leon.ms_accounts.domain.dto.request.AccountMovementEvent;
import com.leon.ms_accounts.exception.InsufficientBalanceException;
import com.leon.ms_accounts.service.accountMovement.AccountMovementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
public class AccountMovementController {
    private final AccountMovementService accountMovementService;

    @PostMapping
    public ResponseEntity<AccountMovement> createAccountMovement(@RequestBody AccountMovement accountMovement)
            throws InsufficientBalanceException {
        AccountMovement savedAccountMovement = accountMovementService.saveAccountMovement(accountMovement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccountMovement);
    }

    @GetMapping
    public Page<AccountMovement> getAllAccountMovementes(@PageableDefault(size = 20) Pageable pageable) {
        return accountMovementService.getAllAccountMovements(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountMovement> getAccountMovementById(@PathVariable Long id) {
        return accountMovementService.getAccountMovementById(id)
                .map(accountMovemente -> ResponseEntity.ok().body(accountMovemente))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountMovement> updateAccountMovement(@PathVariable Long id, @RequestBody AccountMovement accountMovementDetails) {
        AccountMovement updatedAccountMovement = accountMovementService.updateAccountMovement(id, accountMovementDetails);
        return ResponseEntity.ok(updatedAccountMovement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountMovement(@PathVariable Long id) {
        accountMovementService.deleteAccountMovement(id);
        return ResponseEntity.noContent().build();
    }
}
