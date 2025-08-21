package com.vakya.webfluxProject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id=id;
        this.name=name;
    }
}
