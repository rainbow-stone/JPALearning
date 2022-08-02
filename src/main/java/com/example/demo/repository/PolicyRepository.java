package com.example.demo.repository;

import com.example.demo.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jie.lin
 */
public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
