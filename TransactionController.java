package com.codewithrebecca.transactionapi.controller;

import com.codewithrebecca.transactionapi.dto.TransactionRequest;
import com.codewithrebecca.transactionapi.model.Transaction;
import com.codewithrebecca.transactionapi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/process")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction processTransaction(@Valid @RequestBody TransactionRequest request) {
        return transactionService.processTransaction(request);
    }
}