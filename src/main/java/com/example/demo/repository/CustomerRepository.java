package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author jie.lin 2022/7/5
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>,
  JpaSpecificationExecutor<Customer> {

  /**      JPA提供方法解析完成简单查询                          **/
  List<Customer> findAllByCustomerId(Long customerId);

  /**      JPQL查询                                         **/
  @Query("select c from Customer c where c.fullName like concat('%', ?1, '%') or c.fullName like concat('%', ?2)")
  List<Customer> findAllByNameContainingOrNameEndingWith(String fullName);

  @Query(value = "SELECT c FROM Customer c WHERE c.fullName = ?1 and c.idType = ?2 and c.idCode = ?3",
        countQuery = "SELECT c FROM Customer c WHERE c.fullName = ?1 and c.idType = ?2 and c.idCode = ?3")
  List<Customer> findAllByMultipleConditionSample1(String fullName, Integer idType, String idCode);

  @Query(value = "SELECT c FROM Customer c WHERE c.fullName = :fullName and c.idType = :idType and c.idCode = :idCode",
    countQuery = "SELECT c FROM Customer c WHERE c.fullName = :fullName and c.idType = :idType and c.idCode = :idCode")
  List<Customer> findAllByMultipleConditionSample2(String fullName, Integer idType, String idCode);

  @Query(value = "SELECT c FROM Customer c WHERE c.fullName = :#{#fullName} and c.idType = :#{#idType} and c.idCode = :#{#idCode}",
    countQuery = "SELECT c FROM Customer c WHERE c.fullName = :#{#fullName} and c.idType = :#{#idType} and c.idCode = :#{#idCode}")
  List<Customer> findAllByMultipleConditionSample3(String fullName, Integer idType, String idCode);

  @Query(value = "SELECT c FROM Customer c WHERE c.fullName = :#{#customer.fullName} and c.idType = :#{#customer.idType} and c.idCode = :#{#customer.idCode}",
    countQuery = "SELECT c FROM Customer c WHERE c.fullName = :#{#customer.fullName} and c.idType = :#{#customer.idType} and c.idCode = :#{#customer.idCode}")
  List<Customer> findAllByMultipleConditionSample4(Customer customer);

  /**      Native SQL查询                                   **/
  @Query(value = "SELECT c FROM t_customer c WHERE c.full_name = ?1 and c.id_type = ?2 and c.id_code = ?3",
          countQuery = "SELECT c FROM t_customer c WHERE c.full_name = ?1 and c.id_type = ?2 and c.id_code = ?3",nativeQuery = true)
  List<Customer> findAllByMultipleConditionSample5(String fullName, Integer idType, String idCode);

}
