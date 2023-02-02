package com.example.application.JPA.entities.mensen;

import com.example.application.JPA.entities.MailUser;
import com.example.application.JPA.entities.meals.Meals_Mensa_Academica;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mensa_academica")
public class Mensa_Academica extends Mensa {

    @OneToMany(mappedBy = "mensa_academica")
    private Set<Meals_Mensa_Academica> meals_mensa_academica;

    @OneToMany(mappedBy = "mensa_academica", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MailUser> mail_users;
}