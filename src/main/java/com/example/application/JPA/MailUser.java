package com.example.application.JPA;

import javax.persistence.*;

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

    public MailUser() {
    }

    public MailUser(String email, String firstname, String lastname,
                    boolean enabled, ActivationCode activationCode, DeactivationCode deactivationCode) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.enabled = enabled;
        this.activationCode = activationCode;
        this.deactivationCode = deactivationCode;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public ActivationCode getActivationCode() {
        return activationCode;
    }
    public void setActivationCode(ActivationCode activationCode) {
        this.activationCode = activationCode;
    }
    public DeactivationCode getDeactivationCode() {
        return deactivationCode;
    }
    public void setDeactivationCode(DeactivationCode deactivationCode) {
        this.deactivationCode = deactivationCode;
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                '}';
    }
}