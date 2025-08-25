package com.vakya.webfluxProject.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash("Student")
public class StudentRedis {
    @Id
    private String id;
    private String name;
    private int age;
}
