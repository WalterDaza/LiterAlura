package com.alura.challenge.literatura.repository;

import com.alura.challenge.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface IAutorRepository extends JpaRepository <Autor, Long> {

    Optional<Autor> findByName (String name);

    List<Autor> findByNameContainsIgnoreCase (String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :fecha AND a.fechaFallecimiento >= :fecha")
    List<Autor> autoresPorFecha (int fecha);

}
