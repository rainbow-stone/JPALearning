package com.example.demo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jie.lin 2022/8/2
 */
@Data public class CustomerDto implements Serializable {

  private final Long customerId;
  private final String name;
  private final Boolean needAuthCheck;
}
