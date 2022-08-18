package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PolicyDto implements Serializable {
  private Long policyId;
  private String ProposalNo;
  private Date submissionDate;
  private Date issueDate;

  private Date submissionDateStart;
  private Date submissionDateEnd;
  private Date issueDateStart;
  private Date issueDateEnd;

  private Integer idType;
  private String idCode;
}
