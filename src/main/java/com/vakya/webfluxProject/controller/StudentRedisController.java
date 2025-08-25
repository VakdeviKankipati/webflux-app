package com.vakya.webfluxProject.controller;

import com.vakya.webfluxProject.model.StudentRedis;
import com.vakya.webfluxProject.service.StudentRedisService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StudentRedisController {

    private final StudentRedisService studentRedisService;

    public StudentRedisController(StudentRedisService studentRedisService) {
        this.studentRedisService = studentRedisService;
    }

    @PostMapping("redis/add")
    public Mono<StudentRedis> createStudentUsingRedis(@RequestBody StudentRedis studentRedis){
        return studentRedisService.createStudent(studentRedis);
    }

    @GetMapping("/redis/all")
    public Flux<StudentRedis> getAllRedisStudents(){
        return studentRedisService.getAllRedisStudents();
    }

    @GetMapping("/redis/{id}")
    public Mono<StudentRedis> getStudentById(@PathVariable String id) {
        return studentRedisService.getStudentById(id);
    }


    @PutMapping("redis/update/{id}")
    public Mono<StudentRedis> updateStudent(@PathVariable String id, @RequestBody StudentRedis studentRedis) {
        return studentRedisService.updateStudent(id, studentRedis);
    }

    @DeleteMapping("redis/delete/{id}")
    public Mono<Void> deleteStudent(@PathVariable String id) {
        return studentRedisService.deleteStudent(id)
                .then();
    }
}
