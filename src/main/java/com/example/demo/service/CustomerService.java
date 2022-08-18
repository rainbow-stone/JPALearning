package com.example.demo.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.QCustomer;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Querydsl示例 */
@Service
public class CustomerService {

  @Autowired private JPAQueryFactory queryFactory;

  /**
   * 返回Entity对象
   *
   * @param condition
   * @return
   */
  public List<CustomerDto> findCustomerByCondition(CustomerDto condition) {
    QCustomer customer = QCustomer.customer;
    List<Customer> customers = new ArrayList<>();
    List<CustomerDto> customerDtos = new ArrayList<>();
    customers =
        queryFactory
            .selectFrom(customer)
            .where(
                customer
                    .firstName
                    .like(StrUtil.concat(true, "%", condition.getFirstName(), "%"))
                    .or(
                        customer.lastName.like(
                            StrUtil.concat(true, "%", condition.getLastName(), "%"))))
            .fetch();
    BeanUtil.copyProperties(customers, customerDtos);
    return customerDtos;
  }

  /**
   * 返回Entity字段
   *
   * @param condition
   * @return
   */
  public List<String> findCustomerFullNameByCondition(CustomerDto condition) {
    QCustomer customer = QCustomer.customer;
    Predicate predicate = customer.isNotNull().or(customer.isNull());

    // querydsl Condition
    Optional.ofNullable(condition.getFullName())
        .ifPresent(
            fullName ->
                ExpressionUtils.or(
                    predicate,
                    customer.fullName.like(
                        StrUtil.concat(true, "%", condition.getFullName(), "%"))));
    Optional.ofNullable(condition.getFirstName())
        .ifPresent(
            firstName ->
                ExpressionUtils.or(
                    predicate,
                    customer.firstName.like(
                        StrUtil.concat(true, "%", condition.getFirstName(), "%"))));
    Optional.ofNullable(condition.getLastName())
        .ifPresent(
            lastName ->
                ExpressionUtils.or(
                    predicate,
                    customer.lastName.like(
                        StrUtil.concat(true, "%", condition.getLastName(), "%"))));

    // Order Condition, Pageable
    return queryFactory
        .select(customer.fullName)
        .from(customer)
        .where(predicate)
        .orderBy(customer.customerId.asc(), customer.firstName.asc())
        .offset(10)
        .limit(5)
        .fetch();
  }

  /**
   * 返回Entity字段DTO
   *
   * @param condition
   * @return
   */
  public List<CustomerDto> findCustomerByConditionForProjections(CustomerDto condition) {
    QCustomer customer = QCustomer.customer;
    List<CustomerDto> customerDtos;
    customerDtos =
        queryFactory
            .select(
                Projections.constructor(
                    CustomerDto.class, customer.idType, customer.idCode, customer.fullName))
            .from(customer)
            .where(
                customer.firstName.like(StrUtil.concat(true, "%", condition.getFirstName(), "%")))
            .fetch();
    return customerDtos;
  }
}
