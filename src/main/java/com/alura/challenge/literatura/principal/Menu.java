package com.alura.challenge.literatura.principal;


import com.alura.challenge.literatura.model.Autor;
import com.alura.challenge.literatura.service.AutorService;
import com.alura.challenge.literatura.service.LibroService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Menu {
    private final LibroService libroService;
    private final AutorService autorService;
    Scanner scanner = new Scanner(System.in);

    public Menu(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void correrMenu () {

        int opcion = -1;

        while (opcion !=0){

            String menu = """
                1 - Buscar libro por titulo
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en un determinado año
                5 - Listar libros por idioma
                6 - Top 5 libros con más descargas
                7 - Buscar autor por nombre
                
                0 - Salir
                """;
            System.out.println(menu);
            System.out.print("Ingrese la opción deseada: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("--------------------------------------------------");
                    System.out.print("Ingrese el nombre del libro que desea buscar: ");
                    String titulo = scanner.nextLine();
                    libroService.buscarLibroPorTitulo(titulo);
                    System.out.println("--------------------------------------------------");
                    break;
                case 2:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Estos son los libros registrados hasta el momento: ");
                    libroService.listarLibros();
                    System.out.println("--------------------------------------------------");
                    break;
                case 3:
                    System.out.println("--------------------------------------------------");
                    System.out.println("Estos son los autores registrados hasta el momento: ");
                    autorService.listarAutores();
                    System.out.println("--------------------------------------------------");
                    break;
                case 4:
                    System.out.println("--------------------------------------------------");
                    System.out.print("Ingresa el año vivo de autor(es) que desea buscar: ");
                    int fecha = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("--------------------------------------------------");
                    System.out.print("Estos son los aturoes vivos en el año " + fecha + " :");
                    autorService.autoresPorFecha(fecha);
                    System.out.println("--------------------------------------------------");
                    break;
                case 5:
                    System.out.println("--------------------------------------------------");
                    libroService.listaDeIdiomas();
                    System.out.print("Ingresa el idioma para buscar los libros: ");
                    String idioma = scanner.nextLine();
                    System.out.println("--------------------------------------------------");
                    System.out.print("Estos son los libros encontrados escritos en" + idioma);
                    libroService.librosPorIdioma(idioma);
                    System.out.println("--------------------------------------------------");
                    break;
                case 6:
                    System.out.println("--------------------------------------------------");
                    System.out.print("Estos son los libros mas descargados en la bd: ");
                    libroService.top5LibrosDescargados();
                    System.out.println("--------------------------------------------------");
                    break;
                case 7:
                    System.out.print("Ingresa el nombre del autor a buscar: ");
                    String nombre = scanner.nextLine();
                    List<Autor> autorBuscado = autorService.buscarAutorNombre(nombre);
                    if(!autorBuscado.isEmpty()){
                        System.out.println("--------------------------------------------------");
                        System.out.println("Autor(es) encontrado(s): ");
                        autorBuscado.forEach(System.out::println);
                        System.out.println("--------------------------------------------------");
                    } else {
                        System.out.println("--------------------------------------------------");
                        System.out.println("No se a encontrado el autor con nombre: " + nombre);
                        System.out.println("--------------------------------------------------");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }

    }


}
