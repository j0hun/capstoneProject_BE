package com.example.demo.dto;

import com.example.demo.constant.Allergy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodRequestDTO {

    private String name;
    private Integer price;
    private Integer calorie;
    private List<Allergy> allergyList;
    private Long restaurantId;

}
