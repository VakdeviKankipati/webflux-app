package com.vakya.webfluxProject.controller;

import com.vakya.webfluxProject.dto.Customer;
import com.vakya.webfluxProject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAllCustomer(){
        return customerService.loadAllCustomers();
    }

    @GetMapping(value = "/streams", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomerStreams(){
        return customerService.loadAllCustomersStream();
    }
}
