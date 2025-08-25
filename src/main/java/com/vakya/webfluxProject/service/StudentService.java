package com.vakya.webfluxProject.service;

import com.vakya.webfluxProject.model.StudentMDB;
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

    public Mono<StudentMDB> createStudent(StudentMDB studentMDB) {
        return repository.save(studentMDB);
    }

    public Flux<StudentMDB> getAllStudents() {
        return repository.findAll();
    }

    public Flux<StudentMDB> findStudentsName(String name) {
        return repository.findByName(name);
    }

    public Mono<StudentMDB> getStudentsId(String id) {
        return repository.findById(id);
    }

    public Mono<StudentMDB> updateStudent(String id, StudentMDB updatedStudentMDB) {
        return repository.findById(id)
                .flatMap(existingStudentMDB -> {
                    if (updatedStudentMDB.getName() != null) {
                        existingStudentMDB.setName(updatedStudentMDB.getName());
                    }
                    if (updatedStudentMDB.getAge() != 0) {
                        existingStudentMDB.setAge(updatedStudentMDB.getAge());
                    }
                    return repository.save(existingStudentMDB);
                });
    }

    public Mono<Void> deleteStudent(String id) {
        return repository.deleteById(id);
    }
}
