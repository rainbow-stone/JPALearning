package com.example.demo.repository;

import com.example.demo.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

/**
 * @author jie.lin
 */
public interface PolicyRepository
    extends JpaRepository<Policy, Long>, JpaSpecificationExecutor<Policy> {

  List<Policy> findAllByProposalNoAndIssueDateBefore(String proposalNo, Date issueDateStart);

  List<Policy> findByPolicyHolderFullName(String name);
}
