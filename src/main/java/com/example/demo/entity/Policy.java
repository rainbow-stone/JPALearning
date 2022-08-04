package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "policyHolderId")
    private Customer policyHolder;

    private String proposalNo;

    private Date submissionDate;

    private Date issueDate;

}
