package com.devops.rest_service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


    public class Transaction { 
    @NotBlank(message = "Transaction cannot be blank")
    @Size(min = 10, max = 50, message = "Transaction ID must be between 10 and 50 characters")
    @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "Invalid transaction ID format. Must be a valid UUID."
    )
    private String transactionID;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be strictly greater than zero")
    private BigDecimal amount;
    

     // Standard Getters and Setters
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Transaction(String transactionID, BigDecimal amount) {
        this.transactionID = transactionID;
        this.amount = amount;
    }

}