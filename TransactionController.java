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

    @PostMapping("/Transaction")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction Transaction) {
        log.debug("REST request to save Transaction : {}", Transaction);
        return TransactionService.createTransaction(Transaction);
    }

    @PutMapping("/Transaction")
    public ResponseEntity<?> updateTransaction(@Valid @RequestBody Transaction Transaction) {
        log.debug("REST request to update Transaction : {}", Transaction);
        return TransactionService.updateTransaction(Transaction);
    }

    @GetMapping("/Transaction/{TransactionId}")
    public ResponseEntity<Transaction> getTransaction(@Valid @PathVariable Long TransactionId) {
        log.debug("REST request to find Transaction with id : {}", TransactionId);
        return TransactionService.getTransaction(TransactionId);

    }

    @GetMapping("/Transaction")
    public ResponseEntity<ArrayList<Transaction>> getObjectCategories() {
        log.debug("REST request to find all objectCategories");
        return TransactionService.getObjectCategories();
    }

    @DeleteMapping("/Transaction/{TransactionId}")
    public ResponseEntity<?> deleteTransaction(@Valid @PathVariable Long TransactionId) {
        log.debug("REST request to delete Transaction with id : {}", TransactionId);
        return TransactionService.deleteTransaction(TransactionId);
    }
}
