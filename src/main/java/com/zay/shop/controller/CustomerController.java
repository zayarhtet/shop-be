package com.zay.shop.controller;

import com.zay.shop.model.Customer;
import com.zay.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCustomer(@PathVariable(value = "id") Integer id) throws Exception {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value = "id") Integer id, @RequestBody Customer customer) throws Exception {
        return customerService.updateCustomer(id,customer);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deleteCustomer(@PathVariable(value = "id") Integer id) throws Exception {
        return customerService.deleteCustomer(id);
    }
}
