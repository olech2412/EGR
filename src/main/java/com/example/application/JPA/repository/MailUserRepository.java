package com.example.application.JPA.repository;

import com.example.application.JPA.MailUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MailUserRepository extends CrudRepository<MailUser, Long> {

    List<MailUser> findByEmail(String email);

    MailUser findByActivationCode_Code(String code);

    MailUser findByVotingCode_Code(String code);

    MailUser findByDeactivationCode_Code(String code);
    /**
     * Finds all enabled users
     * @param enabled
     * @return
     */
    Iterable<MailUser> findUsersByEnabled(Boolean enabled);
}