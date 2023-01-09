package com.example.application.JPA.repository;

import com.example.application.JPA.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}