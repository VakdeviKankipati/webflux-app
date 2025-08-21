package com.vakya.webfluxProject.repository;

import com.vakya.webfluxProject.model.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
    Flux<Student> findByName(String name);
}
