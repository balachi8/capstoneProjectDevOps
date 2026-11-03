package com.devops.rest_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class TransactionValidationTests {
    
	private Validator validator; 

	  // Create two new BigDecimals
        BigDecimal bd1 = 
               new BigDecimal("2334560.0987654321");
        BigDecimal bd2 = 
               new BigDecimal("-20000");

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsAreValid_thenNoValidationViolations() {
        // Arrange
        Transaction txn = new Transaction("00000000", bd1);

        // Act
        Set<ConstraintViolation<Transaction>> violations = validator.validate(txn);

        // Assert
        assertThat(violations).isEmpty();
    }

	@Test
    void whenTransactionIsBlank_thenValidationFails() {
        // Arrange
        Transaction txn = new Transaction("123456789123456789", bd1);

        // Act
        Set<ConstraintViolation<Transaction>> violations = validator.validate(txn);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).contains("Invalid");
    }

	@Test
    void whenTransactionIsInvalid_thenValidationFails() {
        // Arrange
        Transaction txn = new Transaction("ABC123456789234", bd1);

        // Act
        Set<ConstraintViolation<Transaction>> violations = validator.validate(txn);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Invalid transaction ID format. Must be a valid UUID.");
    }

	@Test
    void whenAmountIsNegative_thenValidationFails() {
        // Arrange
        Transaction txn = new Transaction("f81d4fae-7dec-11d0-a765-00a0c91e6bf6", bd2);

        // Act
        Set<ConstraintViolation<Transaction>> violations = validator.validate(txn);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Amount must be strictly greater than zero");
    }
}