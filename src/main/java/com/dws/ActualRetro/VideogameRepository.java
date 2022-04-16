package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public interface VideogameRepository extends JpaRepository<Videogame, Long> {
    Videogame findById(long id);
    @Query(value = "SELECT * FROM Videogame WHERE price BETWEEN :pricemin AND :pricemax", nativeQuery = true)
    List<Videogame> findVideogameBetweenPrices(@Param("pricemin") float pricemin, @Param("pricemax") float pricemax);
    @Query(value = "SELECT * FROM Videogame WHERE pegi=:pegi", nativeQuery = true)
    List<Videogame> findGamesPegi(@Param("pegi") int pegi);
}
