package com.dws.ActualRetro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface VideogameRepository extends JpaRepository<Videogame, Long> {

}
