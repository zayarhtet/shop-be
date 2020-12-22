package com.zay.shop.service;

import com.zay.shop.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<Customer> getAllCustomers();
    ResponseEntity<?> getCustomerById(Integer id) throws Exception;
    ResponseEntity<?> saveCustomer(Customer customer);
    ResponseEntity<?> updateCustomer(Integer id, Customer updatedCustomer) throws Exception;
    Map<String,Boolean> deleteCustomer(Integer id) throws Exception;
}
