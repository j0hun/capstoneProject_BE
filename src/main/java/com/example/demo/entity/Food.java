package com.example.demo.entity;

import com.example.demo.constant.Allergy;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Integer price;

    private Integer calorie;

    @ElementCollection(targetClass = Allergy.class)
    @CollectionTable(name = "food_allergies", joinColumns = @JoinColumn(name = "food_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "allergy")
    private List<Allergy> allergyList;

    @OneToMany(mappedBy = "food",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<SubFood> subFoodList = new ArrayList<>();

    public void updateFood(Food food){
        this.category = food.getCategory();
        this.name = food.getName();
        this.price = food.getPrice();
        this.calorie = food.getCalorie();
        this.allergyList = food.getAllergyList();
        this.subFoodList = food.getSubFoodList();
    }

    public Food(String name, String category, Integer price, Integer calorie, List<Allergy> allergyList) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.calorie = calorie;
        this.allergyList = allergyList;
    }

}
