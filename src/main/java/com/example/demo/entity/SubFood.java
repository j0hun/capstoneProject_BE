package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SubFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    public SubFood(String name, Food food) {
        this.name = name;
        this.food = food;
    }

    public void updateSubFood(SubFood subFood){
        this.name = subFood.getName();;
        this.food = subFood.getFood();
    }

}
