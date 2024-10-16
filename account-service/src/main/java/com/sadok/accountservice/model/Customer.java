package com.sadok.accountservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

}
