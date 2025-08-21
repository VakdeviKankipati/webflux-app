package com.vakya.webfluxProject.handler;

import com.vakya.webfluxProject.dto.Customer;
import com.vakya.webfluxProject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerRepository customerRepository;

    public Mono<ServerResponse> loadCustomer(ServerRequest request){
        Flux<Customer> customerFlux = customerRepository.getCustomerList();
        return  ServerResponse.ok().body(customerFlux,Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
       int customerId = Integer.valueOf(request.pathVariable("input"));
//        customerRepository.getCustomerList().filter(c->c.getId()==customerId).take(1).single();
       Mono<Customer> customerMono = customerRepository.getCustomerList().filter(c->c.getId()==customerId).next();
       return ServerResponse.ok().body(customerMono,Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto->dto.getId()+":"+dto.getName());
        return ServerResponse.ok().body(saveResponse,String.class);
    }

}
