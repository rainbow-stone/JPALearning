package com.example.demo.service;

import cn.hutool.core.util.StrUtil;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.QCustomer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private JPAQueryFactory queryFactory;

    public List<CustomerDto> findCustomerByCondition(CustomerDto condition) {
        QCustomer customer = QCustomer.customer;

        return queryFactory.select(customer).where(customer.firstName.like(
                StrUtil.concat(true, "%",condition.getFirstName(), "%"));
    }
}
