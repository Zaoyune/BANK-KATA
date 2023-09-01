package com.example.katademo;

import com.example.katademo.Entities.Account;
import com.example.katademo.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KatademoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KatademoApplication.class, args);
    }
    @Autowired
    public AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        Account A1 = accountRepository.save(new Account(null,"Hassan","Benharouga",100000,null));
    }
}
