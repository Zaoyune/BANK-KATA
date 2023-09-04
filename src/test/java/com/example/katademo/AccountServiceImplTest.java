package com.example.katademo;

import com.example.katademo.DTO.TransactionResponseDTO;
import com.example.katademo.Entities.Account;
import com.example.katademo.Entities.Transaction;
import com.example.katademo.Enums.TransactionType;
import com.example.katademo.Mappers.AccountMapper;
import com.example.katademo.Repositories.AccountRepository;
import com.example.katademo.Repositories.TransactionRepository;
import com.example.katademo.Services.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDepositMoney() {
        Long accountId = 1L;
        int amount = 100;
        int initialBalance = 500;
        Account account = new Account(accountId,"Hassan","Benharouga",  initialBalance, new ArrayList<>());
        TransactionType transactionType = TransactionType.DEBIT;

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        ResponseEntity<String> response = accountService.depositMoney(accountId, amount);

        assertEquals(ResponseEntity.ok("Money deposited successfully, new balance : "+(initialBalance+amount)), response);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testWithdrawMoney() {
        Long accountId = 1L;
        int amount = 50;
        int initialBalance = 250;
        Account account = new Account(accountId,"Hassan","Benharouga",  initialBalance, new ArrayList<>());
        TransactionType transactionType = TransactionType.CREDIT;

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        ResponseEntity<String> response = accountService.withdrawMoney(accountId, amount);
        System.out.println("Account balance after withdrawal: " + account.getBalance());
        assertEquals(ResponseEntity.ok("Money withdrawn successfully, new balance : "+(initialBalance-amount)), response);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(accountRepository, times(1)).save(account);

    }

    @Test
    void testWithdrawMoneyWithInsufficientBalance() {
        Long accountId = 1L;
        int initialBalance = 100;
        int amount = 150;

        Account account = new Account(accountId,"Hassan","Benharouga",  initialBalance, new ArrayList<>());

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        assertThrows(RuntimeException.class, () -> accountService.withdrawMoney(accountId, amount));

        verify(transactionRepository, never()).save(any(Transaction.class));
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void testGetCurrentBalance() {
        Long accountId = 1L;
        Account account = new Account(accountId,"Hassan","Benharouga",  300, new ArrayList<>());

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        int balance = accountService.getCurrentBalance(accountId);

        assertEquals(300, balance);
    }

    @Test
    void testGetTransactionsByAccountId() {
        Long accountId = 1L;
        Long transactionId = 1L;
        Account account = new Account(accountId,"Hassan","Benharouga", 500, new ArrayList<Transaction>());
        Transaction transaction = new Transaction(transactionId,LocalDateTime.now(), TransactionType.DEBIT, 100,null);

        TransactionResponseDTO responseDTO = new TransactionResponseDTO(transaction.getId(),transaction.getTimestamp(),transaction.getTransactionType(),transaction.getAmount());

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        when(accountMapper.fromTransactionToTransactionResponseDTO(transaction)).thenReturn(responseDTO);

        when(accountMapper.fromTransactionToTransactionResponseDTO(any(Transaction.class))).thenReturn(responseDTO);

        account.getTransactions().add(transaction);

        List<TransactionResponseDTO> transactions = accountService.getTransactionsByAccountId(accountId);
        assertEquals(Collections.singletonList(responseDTO), transactions);
    }
}
