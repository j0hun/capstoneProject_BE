package com.example.demo.repository;

import com.example.demo.entity.SubFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubFoodRepository extends JpaRepository<SubFood,Long> {

    List<SubFood> findAllByFoodId(Long foodId);

}
