package com.example.repositories;

import com.example.model.Premiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface PremiereRepository extends JpaRepository<Premiere, Integer> {

    Optional<Premiere> findByNameOfThePremiere(String nameOfThePremiere);

    @Modifying
    void deleteByNameOfThePremiere(String nameOfThePremiere);

}