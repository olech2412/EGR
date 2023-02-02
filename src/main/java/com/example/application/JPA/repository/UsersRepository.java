package com.example.application.JPA.repository;

import com.example.application.JPA.entities.authentification.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

    /**
     * Finds all enabled users
     *
     * @param enabled
     * @return
     */
    Iterable<Users> findUsersByEnabled(Boolean enabled);
}
