package com.example.application.JPA.entities.mensen;

import com.example.application.JPA.entities.MailUser;
import com.example.application.JPA.entities.meals.Meals_Cafeteria_Dittrichring;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cafeteria_dittrichring")
public class Cafeteria_Dittrichring extends Mensa {

    @OneToMany(mappedBy = "cafeteria_dittrichring")
    @JsonIgnore
    private Set<Meals_Cafeteria_Dittrichring> meals_cafeteria_dittrichrings;

    @OneToMany(mappedBy = "cafeteria_dittrichring", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MailUser> mail_users;

}