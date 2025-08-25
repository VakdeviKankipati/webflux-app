package com.vakya.webfluxProject.service;

import com.vakya.webfluxProject.model.StudentRedis;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class StudentRedisService {
    private final ReactiveRedisTemplate<String, StudentRedis> redisTemplate;

    public StudentRedisService(ReactiveRedisTemplate<String, StudentRedis> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Mono<StudentRedis> createStudent(StudentRedis studentRedis) {
        if (studentRedis.getId() == null) {
            studentRedis.setId(UUID.randomUUID().toString());
        }
        return redisTemplate.opsForValue()
                .set("student:" + studentRedis.getId(), studentRedis)
                .thenReturn(studentRedis);
    }

    public Flux<StudentRedis> getAllRedisStudents() {
        return redisTemplate.keys("student:*")
                .flatMap(key -> redisTemplate.opsForValue().get(key));
    }

    public Mono<StudentRedis> getStudentById(String id) {
        return redisTemplate.opsForValue().get("student:" + id);
    }

    public Mono<StudentRedis> updateStudent(String id, StudentRedis updatedStudent) {
        return redisTemplate.opsForValue()
                .get("student:" + id)
                .flatMap(existingStudent -> {
                    if (updatedStudent.getName() != null) {
                        existingStudent.setName(updatedStudent.getName());
                    }
                    if (updatedStudent.getAge() != 0) {
                        existingStudent.setAge(updatedStudent.getAge());
                    }
                    return redisTemplate.opsForValue()
                            .set("student:" + id, existingStudent)
                            .thenReturn(existingStudent);
                });
    }


    public Mono<Long> deleteStudent(String id) {
        return redisTemplate.delete("student:" + id);
    }

}
