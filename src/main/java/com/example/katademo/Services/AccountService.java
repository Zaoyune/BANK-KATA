package com.example.katademo.Services;


import com.example.katademo.DTO.TransactionResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    ResponseEntity<String> depositMoney(Long accountId, int amount);
    ResponseEntity<String> withdrawMoney(Long accountId, int amount);
    int getCurrentBalance(Long accountId);
    List<TransactionResponseDTO> getTransactionsByAccountId(Long accountId);
}
