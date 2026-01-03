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

    //2. Devolver un libro específico usando @PathVariable
    @GetMapping("/libroEspecifico/{id}")
    public ResponseEntity<Libro> devolverLibroEspecifico(@PathVariable Long id){
        for (Libro l : listaLibros){
            if (l.getId().equals(id)){
                return ResponseEntity.status(HttpStatus.OK).body(l);
            }
        }

        //Si no hay resultado devolver ResponseEntity con 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //3. Filtrar libros por autor y opcionalmente por año con @RequestParam
    @GetMapping("/libros/filtro")
    public ResponseEntity<List<Libro>> devolverLibroFiltrado(@RequestParam String autor,
                                                             @RequestParam(required = false) Integer anio) {
        List<Libro> resultado = new ArrayList<>();

        for (Libro l : listaLibros) {
            if (l.getAutor().equalsIgnoreCase(autor)) {
                //Si no se envia el parametro anio se filtra por autor
                if (anio == null) {
                    resultado.add(l);
                } else if (l.getAnioPublicacion() == anio) {
                    resultado.add(l);
                }

            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    //4. Registrar un nuevo libro con @RequestBody
    @PostMapping("/libros/nuevoRegistro")
    public ResponseEntity<Libro> registrarNuevoLibro(@RequestBody Libro lib){
        listaLibros.add(lib);
        System.out.println("✅Nuevo libro registrado.");
        System.out.println("ISBN: "+lib.getIsbn());
        System.out.println("Título: "+lib.getTitulo());
        System.out.println("Autor: "+lib.getAutor());
        System.out.println("Año de Publicación: "+lib.getAnioPublicacion());
        System.out.println("Disponible: "+lib.isDisponible());

        //Devuelve codigo 201 y libro en el cuerpo
        return ResponseEntity.status(HttpStatus.CREATED).body(lib);
    }

}
