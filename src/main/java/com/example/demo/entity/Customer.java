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
public class Customer extends BaseEntity {

  @Id @GeneratedValue()
  /*@Column(name = "customer_id")*/
  private Long customerId;

  @OneToMany(mappedBy = "policyHolder", cascade = CascadeType.ALL)
  private List<Policy> policies = new ArrayList<>();

  private String fullName;

  private String firstName;

  private String lastName;

  private Integer idType;

  private String idCode;
}
