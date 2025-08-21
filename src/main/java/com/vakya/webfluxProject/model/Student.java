package com.vakya.webfluxProject.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String name;
    private int age;
}
