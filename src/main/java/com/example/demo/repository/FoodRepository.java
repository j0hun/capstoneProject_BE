package com.example.demo.repository;

import com.example.demo.constant.Allergy;
import com.example.demo.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {

    @Query("SELECT f FROM Food f " +
            "WHERE f.price BETWEEN :minPrice AND :maxPrice " +
            "AND f.calorie BETWEEN :minCalories AND :maxCalories " +
            "AND NOT EXISTS (SELECT 1 FROM f.allergyList a WHERE a IN :allergies)")
    List<Food> findAllByPriceRangeAndByCaloriesRangeAndAllergiesNotContained(
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("minCalories") Integer minCalories,
            @Param("maxCalories") Integer maxCalories,
            @Param("allergies") List<Allergy> allergies);

    List<Food> findAllByRestaurantId(Long restaurantId);

}
