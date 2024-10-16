package com.sadok.accountservice;

import com.sadok.accountservice.client.CustomerRestClient;
import com.sadok.accountservice.entity.BankAccount;
import com.sadok.accountservice.enums.AccountType;
import com.sadok.accountservice.model.Customer;
import com.sadok.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository accountRepository, CustomerRestClient customerRestClient) {
        return args -> {
            try {
                List<Customer> customers = customerRestClient.findAllCustomers();
                if (customers != null && !customers.isEmpty()) {
                    customers.forEach(customer -> {
                        List<BankAccount> bankAccounts = List.of(
                                BankAccount.builder()
                                        .currency("MAD")
                                        .accountType(AccountType.CURRENT_ACCOUNT)
                                        .createdAt(LocalDate.now())
                                        .customerId(customer.getId())
                                        .build(),
                                BankAccount.builder()
                                        .currency("MAD")
                                        .accountType(AccountType.SAVING_ACCOUNT)
                                        .createdAt(LocalDate.now())
                                        .customerId(customer.getId())
                                        .build()
                        );
                        // Save all the bank accounts for the customer
                        accountRepository.saveAll(bankAccounts);
                    });
                } else {
                    System.out.println("No customers found.");
                }
            } catch (Exception e) {
                // Handle any errors that might occur during the execution
                System.err.println("Error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }

}
