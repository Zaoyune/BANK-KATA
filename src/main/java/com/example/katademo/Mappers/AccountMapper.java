package com.example.katademo.Mappers;

import com.example.katademo.DTO.TransactionResponseDTO;
import com.example.katademo.Entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface AccountMapper {
    TransactionResponseDTO fromTransactionToTransactionResponseDTO(Transaction transaction);
//MapStruct sert à faire le mapping entre les entités et les DTOs
}
