package com.zay.shop.service;

import com.zay.shop.exception.ResourceNotFoundException;
import com.zay.shop.model.Customer;
import com.zay.shop.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public ResponseEntity<?> getCustomerById(Integer id) throws Exception {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist with ID " + id));
        return ResponseEntity.ok().body(customer);
    }

    @Override
    public ResponseEntity<?> saveCustomer(Customer customer) {
        return ResponseEntity.ok().body(customerRepository.saveAndFlush(customer));
    }

    @Override
    public ResponseEntity<?> updateCustomer(Integer id, Customer updatedCustomer) throws Exception {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist with ID " + id));
        BeanUtils.copyProperties(updatedCustomer,  customer,"id");
        return ResponseEntity.ok().body(customerRepository.saveAndFlush(customer));
    }

    @Override
    public Map<String,Boolean> deleteCustomer(Integer id) throws Exception {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist with ID " + id));
        customerRepository.delete(customer);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
