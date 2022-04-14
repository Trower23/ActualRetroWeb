package com.dws.ActualRetro;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Service
public class VideogameService {
    @Autowired
    VideogameRepository videogameRepository;
    @PersistenceContext
    EntityManager entityManager;




}
