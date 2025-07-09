package com.alura.challenge.literatura.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer cantidadDescargas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores().stream()
                .map(a -> new Autor(a))
                .collect(Collectors.toList());
        this.idioma = datosLibro.idioma().isEmpty() ? null : datosLibro.idioma().get(0);
        this.cantidadDescargas = datosLibro.cantidadDescargas();
    }

    @Override
    public String toString() {
        String autoresStr = autores.stream()
                .map(autor -> autor.getName())
                .collect(Collectors.joining(" - "));

        return  "********* Libro ********* \n" +
                "Titulo: " + titulo + '\n' +
                "Idioma: " + idioma + '\n' +
                "Cantidad de descargas: " + cantidadDescargas + '\n' +
                "Autores: " + autoresStr + '\n' +
                "********* ***** ********* \n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(Integer cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Libro() {
    }
}
