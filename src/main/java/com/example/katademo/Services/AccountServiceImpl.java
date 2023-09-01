package com.example.katademo.Services;


import com.example.katademo.DTO.TransactionResponseDTO;
import com.example.katademo.Entities.Account;
import com.example.katademo.Entities.Transaction;
import com.example.katademo.Enums.TransactionType;
import com.example.katademo.Mappers.AccountMapper;
import com.example.katademo.Repositories.AccountRepository;
import com.example.katademo.Repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{


    public AccountRepository accountRepository;
    public TransactionRepository transactionRepository;
    public AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public ResponseEntity<String> depositMoney(Long accountId, int amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        Transaction transaction = new Transaction(
                null,
                LocalDateTime.now(),
                TransactionType.DEBIT,
                amount,
                account
        );
        transactionRepository.save(transaction);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return ResponseEntity.ok("Money deposited successfully, new balance : " + account.getBalance());
    }

    @Override
    public ResponseEntity<String> withdrawMoney(Long accountId, int amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        Transaction transaction = new Transaction(
                null,
                LocalDateTime.now(),
                TransactionType.CREDIT,
                amount,
                account
        );
        transactionRepository.save(transaction);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return ResponseEntity.ok("Money withdrawn successfully, new balance : " + account.getBalance());
    }

    @Override
    public int getCurrentBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return ((Account) account).getBalance();
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId)
               .orElseThrow(() -> new EntityNotFoundException("Account not found"));

       List<Transaction> transactions = account.getTransactions();
       return transactions.stream()
               .map(accountMapper::fromTransactionToTransactionResponseDTO)
               .collect(Collectors.toList());
    }
}
