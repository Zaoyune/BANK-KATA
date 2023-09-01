package com.example.katademo;

import com.example.katademo.DTO.TransactionResponseDTO;
import com.example.katademo.Entities.Account;
import com.example.katademo.Entities.Transaction;
import com.example.katademo.Mappers.AccountMapper;
import com.example.katademo.Repositories.AccountRepository;
import com.example.katademo.Services.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class KatademoApplicationTests {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void testGetTransactionsByAccountId() {

    }

}
