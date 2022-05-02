package com.dws.ActualRetro;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findById(long id);
    Optional<Users> findByName(String name);
    Optional<Users> findByMail(String mail);
}
