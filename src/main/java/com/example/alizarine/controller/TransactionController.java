package com.example.alizarine.controller;

import com.example.alizarine.domain.Transaction;
import com.example.alizarine.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class TransactionController {
    private final TransactionService TransactionService;

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        log.debug("REST request to save Transaction : {}", transaction);
        return TransactionService.createTransaction(transaction);
    }

    @PutMapping("/transaction")
    public ResponseEntity<?> updateTransaction(@Valid @RequestBody Transaction transaction) {
        log.debug("REST request to update Transaction : {}", transaction);
        return TransactionService.updateTransaction(transaction);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Transaction> getTransaction(@Valid @PathVariable Long transactionId) {
        log.debug("REST request to find Transaction with id : {}", transactionId);
        return TransactionService.getTransaction(transactionId);

    }

    @GetMapping("/transaction")
    public ResponseEntity<ArrayList<Transaction>> getTransactions() {
        log.debug("REST request to find all transactions");
        return TransactionService.getTransactions();
    }

    @DeleteMapping("/transaction/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@Valid @PathVariable Long transactionId) {
        log.debug("REST request to delete Transaction with id : {}", transactionId);
        return TransactionService.deleteTransaction(transactionId);
    }
}
