package com.example.katademo.Controllers;

import com.example.katademo.DTO.TransactionResponseDTO;
import com.example.katademo.Services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Account")
public class AccountController {

        private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

        @PostMapping("/deposit/{accountId}")
        public ResponseEntity<String> depositMoney(@PathVariable  Long accountId, @RequestParam int amount) {
            return accountService.depositMoney(accountId,amount);
        }

        @GetMapping("/balance/{accountId}")
        public ResponseEntity<Integer> getCurrentBalance(@PathVariable Long accountId) {
            int balance = accountService.getCurrentBalance(accountId);
            return ResponseEntity.ok(balance);
        }

      @PostMapping("/withdraw/{accountId}")
        public ResponseEntity<String> withdrawMoney(@PathVariable  Long accountId, @RequestParam int amount) {
          return accountService.withdrawMoney(accountId,amount);
        }

        @GetMapping("/transactions/{accountId}")
        public List<TransactionResponseDTO> getTransactionsByAccountId(@PathVariable Long accountId) {
           return accountService.getTransactionsByAccountId(accountId);
        }
}
