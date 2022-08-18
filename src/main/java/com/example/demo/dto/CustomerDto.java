package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {
  private Long customerId;
  private String fullName;
  private String firstName;
  private String lastName;
  private Integer idType;
  private String idCode;

  public void CustomerDto(Integer idType, String idCode, String fullName) {
    this.idType = idType;
    this.idCode = idCode;
    this.fullName = fullName;
  }
}
