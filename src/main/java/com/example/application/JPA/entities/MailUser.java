package com.example.application.JPA.entities;

import com.example.application.JPA.entities.mensen.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mail_users")
public class MailUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToOne
    private ActivationCode activationCode;

    @OneToOne
    private DeactivationCode deactivationCode;
    private String firstname;
    private String lastname;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafeteria_dittrichring_id")
    private Cafeteria_Dittrichring cafeteria_dittrichring;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mensa_academica_id")
    private Mensa_Academica mensa_academica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mensa_schoenauer_str_id")
    private Mensa_Schoenauer_Str mensa_schoenauer_str;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mensa_am_elsterbecken_id")
    private Mensa_am_Elsterbecken mensa_am_elsterbecken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mensa_am_medizincampus_id")
    private Mensa_am_Medizincampus mensa_am_medizincampus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mensa_am_park_id")
    private Mensa_am_Park mensa_am_park;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mensa_peterssteinweg_id")
    private Mensa_Peterssteinweg mensa_peterssteinweg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mensa_tierklinik_id")
    private Mensa_Tierklinik mensa_tierklinik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menseria_am_botanischen_garten_id")
    private Menseria_am_Botanischen_Garten menseria_am_botanischen_garten;

    public MailUser() {
    }

    public MailUser(String email, String firstname, String lastname, boolean enabled, ActivationCode activationCode, DeactivationCode deactivationCode, List<Mensa> mensa) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.enabled = enabled;
        this.activationCode = activationCode;
        this.deactivationCode = deactivationCode;

        for (Mensa mensa1 : mensa) {
            if (mensa1 instanceof Cafeteria_Dittrichring) {
                this.cafeteria_dittrichring = (Cafeteria_Dittrichring) mensa1;
            } else if (mensa1 instanceof Mensa_Academica) {
                this.mensa_academica = (Mensa_Academica) mensa1;
            } else if (mensa1 instanceof Mensa_Schoenauer_Str) {
                this.mensa_schoenauer_str = (Mensa_Schoenauer_Str) mensa1;
            } else if (mensa1 instanceof Mensa_am_Elsterbecken) {
                this.mensa_am_elsterbecken = (Mensa_am_Elsterbecken) mensa1;
            } else if (mensa1 instanceof Mensa_am_Medizincampus) {
                this.mensa_am_medizincampus = (Mensa_am_Medizincampus) mensa1;
            } else if (mensa1 instanceof Mensa_am_Park) {
                this.mensa_am_park = (Mensa_am_Park) mensa1;
            } else if (mensa1 instanceof Mensa_Peterssteinweg) {
                this.mensa_peterssteinweg = (Mensa_Peterssteinweg) mensa1;
            } else if (mensa1 instanceof Mensa_Tierklinik) {
                this.mensa_tierklinik = (Mensa_Tierklinik) mensa1;
            } else if (mensa1 instanceof Menseria_am_Botanischen_Garten) {
                this.menseria_am_botanischen_garten = (Menseria_am_Botanischen_Garten) mensa1;
            }
        }

    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                '}';
    }
}