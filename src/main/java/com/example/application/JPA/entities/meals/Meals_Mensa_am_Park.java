package com.example.application.JPA.entities.meals;

import com.example.application.JPA.entities.mensen.Mensa_am_Park;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "meals_mensa_am_park")
public class Meals_Mensa_am_Park extends Meal {

    @ManyToOne
    @JoinColumn(name = "mensa_am_park_id", nullable = false)
    private Mensa_am_Park mensa_am_park;

    public Meals_Mensa_am_Park() {

    }

}
