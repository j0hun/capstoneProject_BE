package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Food(String name) {
        this.name = name;
    }
}
