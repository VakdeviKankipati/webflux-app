package com.vakya.webfluxProject.controller;

import com.vakya.webfluxProject.model.StudentMDB;
import com.vakya.webfluxProject.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class StudentMongoController {
    private final StudentService service;

    public StudentMongoController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Mono<StudentMDB> createStudent(@RequestBody StudentMDB studentMDB) {
        return service.createStudent(studentMDB);
    }

    @GetMapping("/")
    public Flux<StudentMDB> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping(value = "/student/streams", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StudentMDB> getAllStudentsSteams() {
        return service.getAllStudents()
                .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/{id}")
    public Mono<StudentMDB> getStudentById(@PathVariable String id) {
        return service.getStudentsId(id);
    }

    @GetMapping("/search/{name}")
    public Flux<StudentMDB> getStudentsByName(@PathVariable String name) {
        return service.findStudentsName(name);
    }

    @PutMapping("/update/{id}")
    public Mono<StudentMDB> updateStudent(@PathVariable String id, @RequestBody StudentMDB updatedStudentMDB) {
        return service.updateStudent(id, updatedStudentMDB);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<Void> deleteStudent(@PathVariable String id) {
        return service.deleteStudent(id);
    }

}
