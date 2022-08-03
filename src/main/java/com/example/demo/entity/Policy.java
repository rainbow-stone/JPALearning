package com.example.demo.entity;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

/**
 * @author jie.lin 2022/7/5
 */
@Entity
@Table(name = "t_policy")
@Data
public class Policy {


  @Id
  @GeneratedValue
  private Long policyId;

  @ManyToOne @JoinColumn(name = "customerId") private Customer policyHolder;

  private String ProposalNo;

  private Date submissionDate;

  private Date issueDate;

}
