package com.dws.ActualRetro;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service

public class ConsoleService {
    @Autowired
    ConsoleRepository consoleRepository;
    @PersistenceContext
    EntityManager entityManager;

    // methods to ask for specific elements. Dynamic queries.


}
