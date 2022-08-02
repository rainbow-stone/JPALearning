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

  @Query("select c from Customer c where c.name like concat('%', ?1, '%') or c.name like concat('%', ?2)")
  List<Customer> findAllByNameContainingOrNameEndingWith(String name);

  @Query(value = "SELECT c FROM Customer c WHERE c.name = ?1 and c.idType = ?2 and c.idCode = ?3",
        countQuery = "SELECT c FROM Customer c WHERE c.name = ?1 and c.idType = ?2 and c.idCode = ?3")
  List<Customer> findAllByMutipleConidtionSample1(String name, Integer idType, String idCode);

  @Query(value = "SELECT c FROM Customer c WHERE c.name = :name and c.idType = :idType and c.idCode = :idCode",
    countQuery = "SELECT c FROM Customer c WHERE c.name = :name and c.idType = :idType and c.idCode = :idCode")
  List<Customer> findAllByMutipleConidtionSample2(String name, Integer idType, String idCode);

  @Query(value = "SELECT c FROM Customer c WHERE c.name = :name and c.idType = :idType and c.idCode = :idCode",
    countQuery = "SELECT c FROM Customer c WHERE c.name = :name and c.idType = :idType and c.idCode = :idCode")
  List<Customer> findAllByMutipleConidtionSample3(String name, Integer idType, String idCode);

  @Query(value = "SELECT c FROM Customer c WHERE c.name = #{#customer.name} and c.idType = #{#customer.idType} and c.idCode = #{#custom.idCode}",
    countQuery = "SELECT c FROM Customer c WHERE c.name = #{#customer.name} and c.idType = #{#customer.idType} and c.idCode = #{#custom.idCode}")
  List<Customer> findAllByMutipleConidtionSample4(Customer customer);

}
