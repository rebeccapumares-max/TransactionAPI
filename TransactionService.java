package com.codewithrebecca.transactionapi.service;

import com.codewithrebecca.transactionapi.dto.TransactionRequest;
import com.codewithrebecca.transactionapi.exception.InsufficientBalanceException;
import com.codewithrebecca.transactionapi.exception.SelfTransferException;
import com.codewithrebecca.transactionapi.model.Transaction;
import com.codewithrebecca.transactionapi.model.TransactionStatus;
import com.codewithrebecca.transactionapi.model.TransactionType;
import com.codewithrebecca.transactionapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final Map<String, BigDecimal> balances = new HashMap<>();

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction processTransaction(TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setSourceAccountId(request.getSourceAccountId());
        transaction.setTargetAccountId(request.getTargetAccountId());
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());

        BigDecimal currentBalance =
                balances.getOrDefault(request.getSourceAccountId(), BigDecimal.ZERO);

        if (request.getType() == TransactionType.TRANSFER
                && request.getSourceAccountId().equals(request.getTargetAccountId())) {
            throw new SelfTransferException("Source and target accounts cannot be the same.");
        }

        if ((request.getType() == TransactionType.WITHDRAWAL
                || request.getType() == TransactionType.TRANSFER)
                && currentBalance.compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for this transaction.");
        }

        if (request.getType() == TransactionType.DEPOSIT) {
            balances.put(
                    request.getSourceAccountId(),
                    currentBalance.add(request.getAmount())
            );
        } else if (request.getType() == TransactionType.WITHDRAWAL) {
            balances.put(
                    request.getSourceAccountId(),
                    currentBalance.subtract(request.getAmount())
            );
        } else if (request.getType() == TransactionType.TRANSFER) {
            balances.put(
                    request.getSourceAccountId(),
                    currentBalance.subtract(request.getAmount())
            );

            BigDecimal targetBalance =
                    balances.getOrDefault(request.getTargetAccountId(), BigDecimal.ZERO);

            balances.put(
                    request.getTargetAccountId(),
                    targetBalance.add(request.getAmount())
            );
        }

        transaction.setStatus(TransactionStatus.COMPLETED);
        return transactionRepository.save(transaction);
    }
}