package com.example.application.JPA.repository;

import com.example.application.JPA.ActivationCode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivationCodeRepository extends CrudRepository<ActivationCode, Long> {

    List<ActivationCode> findByCode(String code);

}
