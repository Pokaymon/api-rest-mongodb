package com.mundialista.api_rest.controllers;

import com.mundialista.api_rest.models.Entrenador;
import com.mundialista.api_rest.services.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    // Obtener todos los entrenadores
    @GetMapping
    public List<Entrenador> obtenerTodosLosEntrenadores() {
        return entrenadorService.obtenerTodosLosEntrenadores();
    }

    // Obtener un entrenador por id
    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerEntrenadorPorId(@PathVariable String id) {
        Optional<Entrenador> entrenador = entrenadorService.obtenerEntrenadorPorId(id);
        return entrenador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo entrenador
    @PostMapping
    public ResponseEntity<Entrenador> crearEntrenador(@RequestBody Entrenador entrenador) {
        Entrenador nuevoEntrenador = entrenadorService.crearEntrenador(entrenador);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEntrenador);
    }

    // Actualizar un entrenador
    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador(@PathVariable String id, @RequestBody Entrenador entrenador) {
        Entrenador entrenadorActualizado = entrenadorService.actualizarEntrenador(id, entrenador);
        return entrenadorActualizado != null ? ResponseEntity.ok(entrenadorActualizado)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar un entrenador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable String id) {
        entrenadorService.eliminarEntrenador(id);
        return ResponseEntity.noContent().build();
    }
}

