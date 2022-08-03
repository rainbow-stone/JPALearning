package com.example.demo.repository;

import com.example.demo.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author jie.lin
 */
public interface PolicyRepository extends JpaRepository<Policy, Long>, JpaSpecificationExecutor<Policy> {

    /**       **/
    List<Policy> findByPolicyHolderFullName(String name);
}
