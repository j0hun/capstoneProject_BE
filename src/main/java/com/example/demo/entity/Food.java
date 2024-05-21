package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer price;

    private Integer calorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public void updateFood(Food food){
        this.name = food.getName();
        this.price = food.getPrice();
        this.calorie = food.getCalorie();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurant.getFoodList().add(this);
    }

    @Builder
    public Food(String name, Integer price, Integer calorie) {
        this.name = name;
        this.price = price;
        this.calorie = calorie;
    }

}
