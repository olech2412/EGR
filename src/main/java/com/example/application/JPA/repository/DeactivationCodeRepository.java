package com.example.application.JPA.repository;

import com.example.application.JPA.DeactivationCode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeactivationCodeRepository extends CrudRepository<DeactivationCode, Long> {

    List<DeactivationCode> findByCode(String code);
}