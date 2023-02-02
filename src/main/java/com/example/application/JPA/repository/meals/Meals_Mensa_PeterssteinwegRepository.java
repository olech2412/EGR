package com.example.application.JPA.repository.meals;

import com.example.application.JPA.entities.meals.Meals_Mensa_Peterssteinweg;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface Meals_Mensa_PeterssteinwegRepository extends CrudRepository<Meals_Mensa_Peterssteinweg, Long> {

    List<Meals_Mensa_Peterssteinweg> findAllMealsByServingDateGreaterThanEqual(LocalDate servingDate);

    List<Meals_Mensa_Peterssteinweg> findAllMealsByServingDate(LocalDate servingDate);

}