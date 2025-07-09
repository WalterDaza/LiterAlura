package com.alura.challenge.literatura.service;

import com.alura.challenge.literatura.model.Autor;
import com.alura.challenge.literatura.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    IAutorRepository autorRepository;

    public void listarAutores () {
        List<Autor> listaAutor = autorRepository.findAll();
        listaAutor.forEach(System.out::println);
    }

    public void autoresPorFecha (int fecha) {
        List<Autor> listaAutor = autorRepository.autoresPorFecha(fecha);
        listaAutor.forEach(System.out::println);
    }

    public List<Autor> buscarAutorNombre (String nombre) {
        List<Autor> autorBuscado = autorRepository.findByNameContainsIgnoreCase(nombre);
        return autorBuscado;
    }

}
