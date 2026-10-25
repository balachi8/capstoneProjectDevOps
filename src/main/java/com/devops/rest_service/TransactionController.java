package com.devops.rest_service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class TransactionController {

    @GetMapping("/health")
    public Map<String, String> health(){
          return Collections.singletonMap("status","ok");
    }
    
   /* @PostMapping("/validate")
    public String validate(@RequestBody TransactionRequest transactionRequest) {
        return "Transaction  validated successfully!";
    }*/

    @PostMapping("/validate")
    public ResponseEntity<String> validate(@Valid @RequestBody TransactionRequest transactionRequest) {
        // Business logic runs only if the amount validation passes successfully
        return ResponseEntity.ok("Transaction  validated successfully.");
    }

}