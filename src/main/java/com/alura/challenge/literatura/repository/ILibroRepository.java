package com.alura.challenge.literatura.repository;

import com.alura.challenge.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

    public interface ILibroRepository extends JpaRepository <Libro, Long> {

        Optional<Libro> findByTitulo (String titulo);
        List<Libro> findAllByIdioma(String idioma);
        @Query("SELECT DISTINCT l.idioma FROM Libro l")
        List<String> obtenerIdiomas();

        List<Libro> findTop5ByOrderByCantidadDescargasDesc();
    }
