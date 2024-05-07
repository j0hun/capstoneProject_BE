package com.example.demo.entity;

import com.example.demo.constant.Allergy;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @ElementCollection(targetClass = Allergy.class)
    @CollectionTable(name = "food_allergies", joinColumns = @JoinColumn(name = "food_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "allergy")
    private List<Allergy> allergyList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public void updateFood(Food food){
        this.name = food.getName();
        this.price = food.getPrice();
        this.calorie = food.getCalorie();
        this.allergyList = food.getAllergyList();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurant.getFoodList().add(this);
    }

    @Builder
    public Food(String name, Integer price, Integer calorie, List<Allergy> allergyList) {
        this.name = name;
        this.price = price;
        this.calorie = calorie;
        this.allergyList = allergyList;
    }

}
