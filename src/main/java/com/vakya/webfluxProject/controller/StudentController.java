package com.vakya.webfluxProject.controller;

import com.vakya.webfluxProject.model.Student;
import com.vakya.webfluxProject.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Mono<Student> createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }

    @GetMapping("/")
    public Flux<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping(value = "/student/streams", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllStudentsSteams() {
        return service.getAllStudents()
                .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/{id}")
    public Mono<Student> getStudentById(@PathVariable String id) {
        return service.getStudentsId(id);
    }

    @GetMapping("/search/{name}")
    public Flux<Student> getStudentsByName(@PathVariable String name) {
        return service.findStudentsName(name);
    }

    @PutMapping("/update/{id}")
    public Mono<Student> updateStudent(@PathVariable String id, @RequestBody Student updatedStudent) {
        return service.updateStudent(id, updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteStudent(@PathVariable String id) {
        return service.deleteStudent(id);
    }

}
