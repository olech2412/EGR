package com.example.application.JPA.repository;

import com.example.application.JPA.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

    Meal findByName(String name);

}
