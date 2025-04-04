package com.mundialista.api_rest.controllers;

import com.mundialista.api_rest.models.Competicion;
import com.mundialista.api_rest.services.CompeticionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/competiciones")
public class CompeticionController {

    @Autowired
    private CompeticionService competicionService;

    // Obtener todas las competiciones
    @GetMapping
    public List<Competicion> obtenerTodasLasCompeticiones() {
        return competicionService.obtenerTodasLasCompeticiones();
    }

    // Obtener una competicion por su id
    @GetMapping("/{id}")
    public ResponseEntity<Competicion> obtenerCompeticionPorId(@PathVariable String id) {
        Optional<Competicion> competicion = competicionService.obtenerCompeticionPorId(id);
        if (competicion.isPresent()) {
            return ResponseEntity.ok(competicion.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Crear una nueva competicion
    @PostMapping
    public ResponseEntity<Competicion> crearCompeticion(@RequestBody Competicion competicion) {
        Competicion competicionCreada = competicionService.crearCompeticion(competicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(competicionCreada);
    }

    // Actualizar una competicion
    @PutMapping("/{id}")
    public ResponseEntity<Competicion> actualizarCompeticion(@PathVariable String id, @RequestBody Competicion competicion) {
        Optional<Competicion> competicionExistente = competicionService.obtenerCompeticionPorId(id);
        if (competicionExistente.isPresent()) {
            Competicion competicionActualizada = competicionService.actualizarCompeticion(id, competicion);
            return ResponseEntity.ok(competicionActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Eliminar una competicion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompeticion(@PathVariable String id) {
        competicionService.eliminarCompeticion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

