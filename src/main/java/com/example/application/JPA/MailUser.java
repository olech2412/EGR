package com.example.application.JPA;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "mail_users")
@Getter
@Setter
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

    @OneToOne
    private VotingCode votingCode;
    private String firstname;
    private String lastname;
    private boolean enabled;

    public MailUser() {
    }

    public MailUser(String email, String firstname, String lastname,
                    boolean enabled, ActivationCode activationCode, DeactivationCode deactivationCode, VotingCode votingCode) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.enabled = enabled;
        this.activationCode = activationCode;
        this.deactivationCode = deactivationCode;
        this.votingCode = votingCode;
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                '}';
    }
}