package com.sadok.accountservice.client;

import com.sadok.accountservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomer(@PathVariable int id);

    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getAllCustomers")
    List<Customer> findAllCustomers();

    default Customer getDefaultCustomer(int id, Exception exception) {
        return Customer.builder()
                .id(id)
                .firstName("Not Available")
                .lastName("Not Available")
                .email("Not Available")
                .build();
    }

    default List<Customer> getAllCustomers() {
        return List.of();
    }

}
