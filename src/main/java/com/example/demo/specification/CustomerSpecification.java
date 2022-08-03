package com.example.demo.specification;

import cn.hutool.core.util.StrUtil;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author jie.lin 2022/8/3
 * 编写Specification条件
 */
public class CustomerSpecification {

    public static <T> Specification<T> buildCustomerSpecification(CustomerDto customer) {

        return (root, query, cb) -> {
            List<Predicate> andPredicateList = new ArrayList<>();
            List<Predicate> orPredicateList = new ArrayList<>();
            if(customer != null){
                //or condition
                Optional.ofNullable(customer.getFullName()).ifPresent(name -> orPredicateList.add(cb.like(root.get("fullName"), StrUtil.concat(true,"%", customer.getFullName(), "%"))));
                Optional.ofNullable(customer.getFirstName()).ifPresent(name -> orPredicateList.add(cb.like(root.get("firstName"), StrUtil.concat(true,"%", customer.getFirstName(), "%"))));
                Optional.ofNullable(customer.getLastName()).ifPresent(name -> orPredicateList.add(cb.like(root.get("lastName"), StrUtil.concat(true,"%", customer.getLastName(), "%"))));


                //and condition
                Optional.ofNullable(customer.getIdType()).ifPresent(idType -> andPredicateList.add(cb.equal(root.get("idType"), idType)));
                Optional.ofNullable(customer.getIdCode()).ifPresent(idCode -> andPredicateList.add(cb.equal(root.get("idCode"), idCode)));

                andPredicateList.add(cb.or(orPredicateList.toArray(new Predicate[0])));

            }
            return cb.and(andPredicateList.toArray(new Predicate[0]));
        };
    }

}