package com.sadok.customerservice.repository;

import com.sadok.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
