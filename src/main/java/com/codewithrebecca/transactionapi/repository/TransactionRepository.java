package com.codewithrebecca.transactionapi.repository;

import com.codewithrebecca.transactionapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}