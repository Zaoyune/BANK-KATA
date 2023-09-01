package com.example.katademo.DTO;

import com.example.katademo.Enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private Long id;
    private LocalDateTime timestamp;
    private TransactionType TransactionType;
    private int amount;
}
