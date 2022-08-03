package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    private final Long customerId;
    private final String fullName;
    private final String firstName;
    private final String lastName;
    private final Integer idType;
    private final String idCode;
}
