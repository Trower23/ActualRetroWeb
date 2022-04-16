package com.dws.ActualRetro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public interface ConsoleRepository extends JpaRepository<VDConsole, Long> {
    VDConsole findById(long id);
    @Query(value = "SELECT * FROM VDConsole WHERE price BETWEEN :pricemin AND :pricemax", nativeQuery = true)
    public List<VDConsole> findConsoleBetweenPrices(@Param("pricemin") int pricemin, @Param("pricemax") int pricemax);


}
