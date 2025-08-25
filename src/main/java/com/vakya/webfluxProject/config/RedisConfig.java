package com.vakya.webfluxProject.config;

import com.vakya.webfluxProject.model.StudentRedis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, StudentRedis> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<StudentRedis> valueSerializer = new Jackson2JsonRedisSerializer<>(StudentRedis.class);
        StringRedisSerializer keySerializer = new StringRedisSerializer();

        RedisSerializationContext<String, StudentRedis> context = RedisSerializationContext
                .<String, StudentRedis>newSerializationContext(keySerializer)
                .value(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer))
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveValueOperations<String, StudentRedis> reactiveValueOps(
            ReactiveRedisTemplate<String, StudentRedis> template) {
        return template.opsForValue();
    }
}




