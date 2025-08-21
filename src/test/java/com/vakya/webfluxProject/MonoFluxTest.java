package com.vakya.webfluxProject;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<String> monoString = Mono.just("javascript");
        monoString.subscribe(System.out::println);
    }

    @Test
    public void testFlux(){
        Flux<String> stringFlux = Flux.just("java", "spring")
                .concatWithValues("aws")
                .concatWith(Flux.error(new RuntimeException("Exception occured")))
                .concatWithValues("cloud")//this wont work
                .log();
        stringFlux.subscribe(System.out::println);
    }
}
