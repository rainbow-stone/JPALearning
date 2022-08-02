package com.example.demo;

import cn.hutool.core.date.DateUtil;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Policy;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class JPATest {

  @Autowired private PodamService podamService;

  @Autowired private CustomerRepository customerRepository;

  @Test
  void contextLoads() {}

  @BeforeEach
  void setup() throws Exception {
    System.out.println("========= Setup");
    for (int i = 0; i < 5; i++) {
      Customer customer = podamService.createFactory().manufacturePojo(Customer.class);
      customer.setName(customer.getName().concat("_" + i));
      for (int j = 0; j < 5; j++) {
        Policy policy = podamService.createFactory().manufacturePojo(Policy.class);
        policy.setProposalNo(DateUtil.formatDate(new Date()).concat("_" + i + "_" + j));
        policy.setPolicyHolder(customer);
      }
      customerRepository.save(customer);
    }
  }

  @AfterEach
  private static void teardown() throws Exception {}

  @Test
  void testSave() {
    Customer customer = new Customer();
    customerRepository.save(customer);
    List<Customer> customerList = customerRepository.findAll();
  }

  @Test
  void testNull() {
    Integer riskLevel = 1;

    if (riskLevel != 1) {
      System.out.println("flag");
    } else if (riskLevel != 3) {
      System.out.println("flag2");
    }
  }

  @Test
  void testBool() {
    Customer c = new Customer();
    if (c.getNeedAuthCheck()) {
      System.out.println("need");
    }
  }
}
