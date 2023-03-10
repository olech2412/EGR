package com.example.application.JPA.services.mensen;

import com.example.application.JPA.entities.mensen.Mensa;
import com.example.application.JPA.entities.mensen.Mensa_Schoenauer_Str;
import com.example.application.JPA.repository.mensen.Mensa_Schoenauer_StrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Mensa_Schoenauer_StrService extends Mensa_Service {

    @Autowired
    Mensa_Schoenauer_StrRepository mensa_schoenauer_strRepository;

    /**
     * @return Mensa Schoenauer Str as Iterable
     */
    @Override
    public Iterable<? extends Mensa> findAll() {
        return mensa_schoenauer_strRepository.findAll();
    }

    /**
     * @return Mensa Schoenauer Str
     */
    @Override
    public Mensa_Schoenauer_Str getMensa() {
        List<Mensa_Schoenauer_Str> mensa_schoenauer_strList = (List<Mensa_Schoenauer_Str>) mensa_schoenauer_strRepository.findAll();
        return mensa_schoenauer_strList.get(0);
    }
}

