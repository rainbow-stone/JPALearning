package com.example.demo.service;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.dto.PolicyDto;
import com.example.demo.entity.Policy;
import com.example.demo.repository.PolicyRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyService {

  @Autowired private PolicyRepository policyRepository;

  @Autowired private JPAQueryFactory queryFactory;

  /**
   * QBE查询
   *
   * @param condition
   * @return
   */
  public List<PolicyDto> findPolicyByCondition(PolicyDto condition) {
    List<PolicyDto> result = new ArrayList<>();
    Policy policy = new Policy();
    BeanUtil.copyProperties(condition, policy);
    Example<Policy> policyExample = Example.of(policy);
    List<Policy> policies = policyRepository.findAll(policyExample);
    BeanUtil.copyProperties(policies, result);
    return result;
  }

  public List<PolicyDto> findPolicyByMatcher(PolicyDto condition) {
    List<PolicyDto> result = new ArrayList<>();
    Policy policy = new Policy();
    BeanUtil.copyProperties(condition, policy);

    ExampleMatcher matcher =
        ExampleMatcher.matching()
            .withMatcher("proposalNo", ExampleMatcher.GenericPropertyMatcher::endsWith);

    Example<Policy> policyExample = Example.of(policy, matcher);
    List<Policy> policies = policyRepository.findAll(policyExample);
    BeanUtil.copyProperties(policies, result);
    return result;
  }
}
