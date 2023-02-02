package com.example.application.JPA.entities.mensen;

import com.example.application.JPA.entities.MailUser;
import com.example.application.JPA.entities.meals.Meals_Mensa_Peterssteinweg;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mensa_peterssteinweg")
public class Mensa_Peterssteinweg extends Mensa {

    @OneToMany(mappedBy = "mensa_peterssteinweg")
    private Set<Meals_Mensa_Peterssteinweg> meals_mensa_peterssteinweg;

    @OneToMany(mappedBy = "mensa_peterssteinweg", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MailUser> mail_users;
}