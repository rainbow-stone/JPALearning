package com.example.demo.specification;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author jie.lin 2022/8/3
 */
public class CustomerSpecification {

    public static <T> Specification<T> buildCustomerSpecification(Customer customer) {

        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<Predicate>();
            if(customer != null){
                Optional.ofNullable(customer.getName()).ifPresent(name -> predicateList.add(cb.equal(root.get("name"), name)));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }

}