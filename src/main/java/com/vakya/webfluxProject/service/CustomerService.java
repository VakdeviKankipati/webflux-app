package com.vakya.webfluxProject.service;

import com.vakya.webfluxProject.dto.Customer;
import com.vakya.webfluxProject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> loadAllCustomers(){
        long start = System.currentTimeMillis();
        List<Customer> customers = customerRepository.getCustomer();
        long end = System.currentTimeMillis();
        System.out.println("total excetion time"+(end-start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerRepository.loadAllCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("total excetion time"+(end-start));
        return customers;
    }
}
