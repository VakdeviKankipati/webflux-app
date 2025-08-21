package com.vakya.webfluxProject.service;

import com.vakya.webfluxProject.model.Student;
import com.vakya.webfluxProject.repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Mono<Student> createStudent(Student student) {
        return repository.save(student);
    }

    public Flux<Student> getAllStudents() {
        return repository.findAll();
    }

    public Flux<Student> findStudentsName(String name) {
        return repository.findByName(name);
    }

    public Mono<Student> getStudentsId(String id) {
        return repository.findById(id);
    }

    public Mono<Student> updateStudent(String id, Student updatedStudent) {
        return repository.findById(id)
                .flatMap(existingStudent -> {
                    if (updatedStudent.getName() != null) {
                        existingStudent.setName(updatedStudent.getName());
                    }
                    if (updatedStudent.getAge() != 0) {
                        existingStudent.setAge(updatedStudent.getAge());
                    }
                    return repository.save(existingStudent);
                });
    }

    public Mono<Void> deleteStudent(String id) {
        return repository.deleteById(id);
    }
}
