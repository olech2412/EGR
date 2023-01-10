package com.example.application.JPA.repository;

import com.example.application.JPA.authentification.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {

}
