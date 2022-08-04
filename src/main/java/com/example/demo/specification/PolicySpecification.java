package com.example.demo.specification;

import com.example.demo.dto.PolicyDto;
import com.example.demo.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author jie.lin 2022/8/3
 */
public class PolicySpecification {

    public static <T> Specification<T> buildPolicySpecification(PolicyDto policy) {
        return (root, query, cb) -> {
            List<Predicate> andPredicateList = new ArrayList<>();
            if (policy != null) {
                Optional.ofNullable(policy.getProposalNo())
                        .ifPresent(proposalNo -> andPredicateList.add(cb.like(root.get("proposalNo"), proposalNo)));
                //between 查询
                if (policy.getSubmissionDateStart() != null && policy.getSubmissionDateEnd() != null) {
                    andPredicateList.add(cb.between(root.get("submissionDate"), policy.getSubmissionDateStart(),
                            policy.getSubmissionDateEnd()));
                }
                if (policy.getIssueDateStart() != null && policy.getIssueDateEnd() != null) {
                    andPredicateList.add(
                            cb.between(root.get("issueDate"), policy.getIssueDateStart(), policy.getIssueDateEnd()));
                }
                //子查询
                if (policy.getIdType() != null && policy.getIdCode() != null) {
                    Subquery<Long> subQuery = query.subquery(Long.class);
                    Root<Customer> customerRoot = subQuery.from(Customer.class);
                    subQuery.select(customerRoot.get("customerId"))
                            .where(cb.equal(customerRoot.get("idType"), policy.getIdType()),
                                    cb.equal(customerRoot.get("idCode"), policy.getIdCode()));
                    Expression<Long> exp = root.get("policyHolderId");
                    andPredicateList.add(exp.in(subQuery));
                }
            }

            return cb.and(andPredicateList.toArray(new Predicate[0]));
        };

    }

}