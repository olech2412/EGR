package com.example.application.JPA;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private String price;
    private String category;
    private LocalDate servingDate;

    private Integer responseCode;
    private Double rating;

    public Meal() {
    }

    public Meal(String name, String description, String price, String category, LocalDate servingDate, Double rating) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.servingDate = servingDate;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Meal: " + "name=" + name + ", description=" + description + ", price=" + price + ", category=" + category + ", servingDate=" + servingDate + ", rating=" + rating;
    }
}
