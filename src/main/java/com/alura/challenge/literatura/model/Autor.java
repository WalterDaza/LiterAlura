package com.alura.challenge.literatura.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

    @Entity
    public class Autor {

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private Integer fechaNacimiento;
        private Integer fechaFallecimiento;

        @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
        private List<Libro> libros;

        public Autor(DatosAutor datosAutor) {
            this.name = datosAutor.nombre();
            this.fechaNacimiento = datosAutor.fechaNacimiento();
            this.fechaFallecimiento = datosAutor.fechaFallecimiento();
        }

        public Autor() {
        }

        @Override
        public String toString() {
            String libroStr = libros.stream()
                    .map(libro -> libro.getTitulo())
                    .collect(Collectors.joining(", "));

            return "Autor: " + name + "\n" +
                    "Fecha de nacimiento: " + fechaNacimiento + "\n" +
                    "Fecha de fallecimiento: " + fechaFallecimiento + "\n" +
                    "Libros: " + libroStr + "\n";
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
