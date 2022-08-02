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

  private String name;

  private Integer idType;

  private String idCode;

  private Boolean needAuthCheck;

  public Boolean getNeedAuthCheck() {
    return null != this.needAuthCheck && this.needAuthCheck;
  }

  public void setNeedAuthCheck(Boolean needAuthCheck) {
    this.needAuthCheck = needAuthCheck;
  }
}
