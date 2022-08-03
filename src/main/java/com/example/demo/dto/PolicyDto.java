package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PolicyDto implements Serializable {
    private final Long policyId;
    private final String ProposalNo;
    private final Date submissionDate;
    private final Date issueDate;

    private final Date submissionDateStart;
    private final Date submissionDateEnd;
    private final Date issueDateStart;
    private final Date issueDateEnd;

    private Integer idType;
    private String idCode;
}
