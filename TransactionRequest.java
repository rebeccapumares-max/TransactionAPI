package com.codewithrebecca.transactionapi.dto;

import com.codewithrebecca.transactionapi.model.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TransactionRequest {

    @NotBlank(message = "Source Account ID cannot be empty.")
    private String sourceAccountId;

    private String targetAccountId;

    @NotNull(message = "Transaction amount is required.")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero.")
    @Digits(integer = 10, fraction = 2, message = "Invalid amount format.")
    private BigDecimal amount;

    @NotNull(message = "Transaction type must be provided (DEPOSIT, WITHDRAWAL, TRANSFER).")
    private TransactionType type;

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}