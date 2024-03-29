package com.example.demo;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="food")
public class Food {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    public Food(String name) {
        this.name = name;
    }
}
