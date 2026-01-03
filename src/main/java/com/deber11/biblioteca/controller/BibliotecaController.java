package com.deber11.biblioteca.controller;

import com.deber11.biblioteca.model.Libro;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {
    //Lista de libros simulada
    private List<Libro> listaLibros = new ArrayList<>();

    public BibliotecaController(){
        listaLibros.add(new Libro(1L, "978-030747472_0", "Cien años de soledad", "Gabriel García", 1967, false));
        listaLibros.add(new Libro(2L, "978-8471661294", "El Principito", "Antoine de Saint-Exupéry", 1943, true));
        listaLibros.add(new Libro(3L, "978-84-17430-628", "Romeo y Julieta", "William Shakespeare", 1597, true));
        listaLibros.add(new Libro(4L, "978-6070732720", "Orgullo y Prejuicio", "Jane Austen", 1813, true));
        listaLibros.add(new Libro(5L, "978-8408216636", "Mujercitas", "Louisa May Alcott", 1868, false));
    }

    //1. Devolver todos los libros con GET
    @GetMapping("/libros")
    @ResponseBody
    public List<Libro> devolverLibros(){
        return listaLibros;
    }


}
