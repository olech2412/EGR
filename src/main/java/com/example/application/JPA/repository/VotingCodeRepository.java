package com.example.application.JPA.repository;

import com.example.application.JPA.VotingCode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VotingCodeRepository extends CrudRepository<VotingCode, Long> {

    List<VotingCode> findByCode(String code);
}