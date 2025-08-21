package com.vakya.webfluxProject.handler;

import com.vakya.webfluxProject.dto.Customer;
import com.vakya.webfluxProject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {
    @Autowired
    private CustomerRepository customerRepository;

    public Mono<ServerResponse> getCustomer(ServerRequest request){
        Flux<Customer> customerFlux = customerRepository.getCustomerStream();
        return  ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerFlux,Customer.class);
    }
}
