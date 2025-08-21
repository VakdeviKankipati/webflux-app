package com.vakya.webfluxProject.repository;

import com.vakya.webfluxProject.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerRepository {

//    public List<Customer> getCustomer(){
//        return IntStream.rangeClosed(1,50)
//                .peek(i->System.out.println("processing count"+i))
//                .mapToObj(i->new Customer(i,"customer"+i))
//                .collect(Collectors.toList());
//    }

    public List<Customer> getCustomer() {
        List<Customer> customers = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            sleepExcetion(i);
            System.out.println("processing count " + i);
            customers.add(new Customer(i, "customer" + i));
        }

        return customers;
    }

   /* public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        List<Customer> customers = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            sleepExcetion(i);
            System.out.println("processing count " + i);
            Customer customer = new Customer(i, "customer" + i);
            customers.add(customer);
        }

        long end = System.currentTimeMillis();
        System.out.println("total execution time " + (end - start));

        return Flux.fromIterable(customers);
    }*/

    public Flux<Customer> loadAllCustomersStream() {


        return Flux.interval(Duration.ofSeconds(1))
                .take(10)
                .map(i -> {
                    int customerIndex = i.intValue() + 1;
                    System.out.println("processing count " + customerIndex);
                    return new Customer(customerIndex, "customer" + customerIndex);
                });
    }

    public Flux<Customer> getCustomerList() {


        return Flux.range(1,10)
                .doOnNext(i->System.out.println("processing count in stream flow:" +i))
                .map(i->new Customer(i,"customer"+i));
    }

    public Flux<Customer> getCustomerStreamList() {


        return Flux.interval(Duration.ofSeconds(1))
                .take(10)
                .map(i -> {
                    int customerIndex = i.intValue() + 1;
                    System.out.println("processing count " + customerIndex);
                    return new Customer(customerIndex, "customer" + customerIndex);
                });
    }

    public Flux<Customer> getCustomerStream() {


        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i->System.out.println("processing count in stream flow:" +i))
                .map(i->new Customer(i,"customer"+i));
    }


    private static void sleepExcetion(int i){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
