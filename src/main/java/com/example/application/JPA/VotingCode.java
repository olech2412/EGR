package com.example.application.JPA;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "voting_codes")
@Getter
@Setter
public class VotingCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String code;


    public VotingCode(String code) {
        this.code = code;
    }

    public VotingCode() {

    }
}
