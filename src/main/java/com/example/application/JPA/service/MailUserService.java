package com.example.application.JPA.service;

import com.example.application.JPA.MailUser;
import com.example.application.JPA.repository.MailUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MailUserService {

    @Autowired
    MailUserRepository mailUserRepository;

    /**
     * Saves a meal to the database
     *
     * @return enabled mail users
     */
    public Iterable<MailUser> findAllUsersThatAreEnabled() {
        return mailUserRepository.findUsersByEnabled(true);
    }
}
