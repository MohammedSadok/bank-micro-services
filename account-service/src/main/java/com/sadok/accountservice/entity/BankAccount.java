package com.sadok.accountservice.entity;

import com.sadok.accountservice.enums.AccountType;
import com.sadok.accountservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double balance;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Transient
    private Customer customer;
    private int customerId;
}
