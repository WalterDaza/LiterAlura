package com.alura.challenge.literatura.service;

import com.alura.challenge.literatura.api.ConsumoAPI;
import com.alura.challenge.literatura.api.ConvierteDatos;
import com.alura.challenge.literatura.model.Autor;
import com.alura.challenge.literatura.model.Libro;
import com.alura.challenge.literatura.model.RespuestaLibros;
import com.alura.challenge.literatura.repository.IAutorRepository;
import com.alura.challenge.literatura.repository.ILibroRepository;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    @Autowired
    ILibroRepository libroRepository;

    @Autowired
    IAutorRepository autorRepository;

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public Libro buscarLibroPorTitulo(String titulo) {
        //1. Se trae el json de la API
        String json = consumoAPI.obtenerDatos(URL_BASE + titulo.replace(" ", "%20"));

        //2. Se convierte el json en una RespuestaLibros
        RespuestaLibros respuestaUrl = convierteDatos.obtenerDatos(json, RespuestaLibros.class);

        //3. Se toma unicamente el primer libro y se convierte a una entidad Libro
        Libro resultadoLibro = respuestaUrl.libros().stream()
                .findFirst()
                .map(l -> new Libro(l))
                .orElse(null);

        if(resultadoLibro == null){
            System.out.println("--------------------------------------------------");
            System.out.println("Libro no encontrado, intentanta nuevamente");
            System.out.println("--------------------------------------------------");
            return null;
        }

        //4. Se valida si el libro ya está registrado en la bd
        if (libroRepository.findByTitulo(resultadoLibro.getTitulo()).isPresent()) {
            System.out.println("--------------------------------------------------");
            System.out.println("No se permiten libros duplicados"); //Si ya está registrado devulve el mensaje
        } else { //Si no esta registrado valida primero si el autor existe, si existe lo reutiliza, si no existe lo guarda
            List<Autor> autoresActualizados = resultadoLibro.getAutores().stream()
                    .map(autor -> autorRepository.findByName(autor.getName())
                            .orElseGet(() -> autorRepository.save(autor)))
                    .collect(Collectors.toList());

            resultadoLibro.setAutores(autoresActualizados);
            libroRepository.save(resultadoLibro);
        }

        System.out.println(resultadoLibro.toString());

        return resultadoLibro;
    }


    public void listarLibros() {
        List<Libro> listaLibros = libroRepository.findAll();
        listaLibros.forEach(System.out::println);
    }

    public void listaDeIdiomas() {
        List<String> listaIdiomas = libroRepository.obtenerIdiomas();
        for (String idioma : listaIdiomas) {
            if (idioma.equals("es") ) {
                System.out.println("es - español");
            } if (idioma.equals("en")){
                System.out.println("en - inglés");
            } if(idioma.equals("fr")){
                System.out.println("fr - francés");
            } if (idioma.equals("pt")){
                System.out.println("pt - portugués");
            }
        }
    }

    public void librosPorIdioma(String idioma) {
        List<Libro> listaLibros = libroRepository.findAllByIdioma(idioma);
        listaLibros.forEach(System.out::println);
    }

    public void top5LibrosDescargados () {
        List<Libro> listaLibros = libroRepository.findTop5ByOrderByCantidadDescargasDesc();
        listaLibros.forEach(System.out::println);
    }

}
