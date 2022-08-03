package com.example.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Policy;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PolicyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {DemoApplication.class, PodamService.class})
public class JPATest {

    @Autowired
    private PodamService podamService;

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private PolicyRepository  policyRepository;

    private static final String customerName = "赵钱孙李张";

    private static final String policyProposal = "ABCDE";

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setup() {
        System.out.println("========= Setup");
        for (int i = 0; i < 5; i++) {
            List<Policy> policies = new ArrayList<Policy>();
            Customer customer = podamService.createFactory().manufacturePojo(Customer.class);
            customer.setName(customerName.substring(i, i + 1));
            for (int j = 0; j < 5; j++) {
                Policy policy = podamService.createFactory().manufacturePojo(Policy.class);
                policy.setProposalNo(customer.getName().concat(policyProposal.substring(j, j + 1)));
                //policy.setPolicyHolder(customer);
                policies.add(policy);
            }
            customer.setPolicies(policies);
            customerRepository.save(customer);
        }
    }

    @AfterEach
    void teardown() {
    }

    @Test
    void testFindCustomer() {
      List<Policy> policies =policyRepository.findByPolicyHolderName("赵");
      assertNotNull(policies);
    }

}
