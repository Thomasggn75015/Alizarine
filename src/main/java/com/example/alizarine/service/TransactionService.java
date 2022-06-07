package com.example.alizarine.service;

import com.example.alizarine.domain.Transaction;
import com.example.alizarine.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public ResponseEntity<Transaction> createTransaction(Transaction requestedTransaction) {
        Transaction transaction = new Transaction();
        return createTransaction(requestedTransaction, transaction);
    }

    public ResponseEntity<String> updateTransaction(Transaction requestedTransaction) {
        Optional<Transaction> transaction = transactionRepository.findById(requestedTransaction.getId());
        if (transaction.isPresent()) {
            Transaction updatedTransaction = transaction.get();
            updateTransactionFields(requestedTransaction, updatedTransaction);
            log.debug("Transaction updated : {}", updatedTransaction);
            return ResponseEntity.ok().body("Transaction updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Transaction> getTransaction(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return ResponseEntity.of(transaction);
    }

    public ResponseEntity<ArrayList<Transaction>> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>(transactionRepository.findAll());
        return ResponseEntity.ok(transactions);
    }

    public ResponseEntity<String> deleteTransaction(Long id) {
        Optional<Transaction> requestedTransaction = transactionRepository.findById(id);
        if (requestedTransaction.isPresent()) {
            transactionRepository.delete(requestedTransaction.get());
            log.debug("Transaction deleted succesfully : {}", requestedTransaction);
            return ResponseEntity.ok("Transaction deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<Transaction> createTransaction(Transaction requestedTransaction, Transaction transaction) {
        try {
            transaction.setOffer(requestedTransaction.getOffer());
            transaction.setUser(requestedTransaction.getUser());
            transaction.setState(requestedTransaction.getState());
            transactionRepository.saveAndFlush(transaction);
            log.debug("New transaction created: {}", transaction);
            return ResponseEntity.ok(transaction);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void updateTransactionFields(Transaction requestedTransaction, Transaction transaction) {
        if (requestedTransaction.getOffer() != null) { transaction.setOffer(requestedTransaction.getOffer()); }
        if (requestedTransaction.getUser() != null) { transaction.setUser(requestedTransaction.getUser()); }
        if (requestedTransaction.getState() != null) { transaction.setState(requestedTransaction.getState()); }
        transactionRepository.saveAndFlush(transaction);
    }
}
