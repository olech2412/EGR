package com.example.application.JPA.repository.meals;

import com.example.application.JPA.entities.meals.Meals_Mensa_am_Medizincampus;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface Meals_Mensa_am_MedizincampusRepository extends CrudRepository<Meals_Mensa_am_Medizincampus, Long> {

    List<Meals_Mensa_am_Medizincampus> findAllMealsByServingDateGreaterThanEqual(LocalDate servingDate);

    List<Meals_Mensa_am_Medizincampus> findAllMealsByServingDate(LocalDate servingDate);

}