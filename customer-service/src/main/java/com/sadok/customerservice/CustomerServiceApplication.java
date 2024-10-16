package com.sadok.customerservice;

import com.sadok.customerservice.config.GlobalConfig;
import com.sadok.customerservice.entity.Customer;
import com.sadok.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);

    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {

            List<Customer> customerList = List.of(
                    Customer.builder()
                            .firstName("mohammed")
                            .lastName("sadok")
                            .email("mohammed@gmail.com")
                            .build(),

                    Customer.builder()
                            .firstName("ahmed")
                            .lastName("amine")
                            .email("ahmed@gmail.com")
                            .build()
            );

            customerRepository.saveAll(customerList);
        };
    }

}
