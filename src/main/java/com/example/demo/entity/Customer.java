package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jie.lin 2022/7/5
 */
@Entity
@Table(name = "t_customer")
@Data
public class Customer {


    @Id
    @GeneratedValue
    /*@Column(name = "customerId")*/
    private Long customerId;

    @OneToMany(mappedBy = "policyHolder")
    private List<Policy> policies = new ArrayList<>();

    private String fullName;

    private String firstName;

    private String lastName;

    private Integer idType;

    private String idCode;


}
