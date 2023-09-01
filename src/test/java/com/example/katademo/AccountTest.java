package com.example.katademo;

import com.example.katademo.Controllers.AccountController;
import com.example.katademo.DTO.TransactionResponseDTO;
import com.example.katademo.Entities.Account;
import com.example.katademo.Entities.Transaction;
import com.example.katademo.Enums.TransactionType;
import com.example.katademo.Repositories.AccountRepository;
import com.example.katademo.Repositories.TransactionRepository;
import com.example.katademo.Services.AccountService;
import com.example.katademo.Services.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDepositMoney() {
        Long accountId = 1L;
        int amount = 100;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Deposit successful");

        when(accountService.depositMoney(accountId, amount)).thenReturn(expectedResponse);

        ResponseEntity<String> response = accountController.depositMoney(accountId, amount);

        assertEquals(expectedResponse, response);
        verify(accountService, times(1)).depositMoney(accountId, amount);
    }

    @Test
    void testGetCurrentBalance() {
        Long accountId = 1L;
        int expectedBalance = 500;
        when(accountService.getCurrentBalance(accountId)).thenReturn(expectedBalance);
        ResponseEntity<Integer> expectedResponse = ResponseEntity.ok(500);

        ResponseEntity<Integer> balance = accountController.getCurrentBalance(accountId);

        assertEquals(expectedResponse, balance);
        verify(accountService, times(1)).getCurrentBalance(accountId);
    }

    @Test
    void testWithdrawMoney() {
        Long accountId = 1L;
        int amount = 50;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Withdrawal successful");

        when(accountService.withdrawMoney(accountId, amount)).thenReturn(expectedResponse);

        ResponseEntity<String> response = accountController.withdrawMoney(accountId, amount);

        assertEquals(expectedResponse, response);
        verify(accountService, times(1)).withdrawMoney(accountId, amount);
    }

    @Test
    void testGetTransactionsByAccountId() {
        Long accountId = 1L;
        List<TransactionResponseDTO> expectedTransactions = Collections.emptyList();

        when(accountService.getTransactionsByAccountId(accountId)).thenReturn(expectedTransactions);

        List<TransactionResponseDTO> transactions = accountController.getTransactionsByAccountId(accountId);

        assertEquals(expectedTransactions, transactions);
        verify(accountService, times(1)).getTransactionsByAccountId(accountId);
    }
}
