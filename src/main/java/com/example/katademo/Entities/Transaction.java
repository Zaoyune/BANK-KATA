package com.example.katademo.Entities;

import com.example.katademo.Enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    @Enumerated(EnumType.STRING)
    private TransactionType TransactionType;
    private int amount;

    public Transaction(LocalDateTime now, TransactionType transactionType, int amount) {

    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;


}
