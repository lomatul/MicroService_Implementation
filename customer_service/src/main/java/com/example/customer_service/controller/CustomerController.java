package com.example.customer_service.controller;

import com.example.customer_service.entity.Customer;
import com.example.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/")
    public Iterable<Customer> getCustomer(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/")
    public Customer saveCustomers(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable("id") String customerId){
        return customerService.findCustomerById(customerId);
    }
}