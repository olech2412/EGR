package com.example.application.JPA.repository;

import com.example.application.JPA.entities.DeactivationCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeactivationCodeRepository extends CrudRepository<DeactivationCode, Long> {

    List<DeactivationCode> findByCode(String code);
}