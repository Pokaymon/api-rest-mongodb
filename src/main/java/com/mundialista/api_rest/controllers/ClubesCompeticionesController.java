package com.mundialista.api_rest.controllers;

import com.mundialista.api_rest.models.ClubesCompeticiones;
import com.mundialista.api_rest.services.ClubesCompeticionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubes_competiciones")
public class ClubesCompeticionesController {

    @Autowired
    private ClubesCompeticionesService clubesCompeticionesService;

    // Crear una nueva relación
    @PostMapping("/{clubId}/{competicionId}")
    public ResponseEntity<ClubesCompeticiones> crearRelacion(
            @PathVariable String clubId, @PathVariable String competicionId) {
        return ResponseEntity.ok(clubesCompeticionesService.crearRelacion(clubId, competicionId));
    }

    // Obtener una relación por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ClubesCompeticiones> obtenerPorId(@PathVariable String id) {
        ClubesCompeticiones relacion = clubesCompeticionesService.obtenerPorId(id);
        if (relacion != null) {
            return ResponseEntity.ok(relacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubesCompeticiones> actualizarRelacion(
            @PathVariable String id,
            @RequestParam String clubId,
            @RequestParam String competicionId) {
        return ResponseEntity.ok(clubesCompeticionesService.actualizarRelacion(id, clubId, competicionId));
    }

    // Obtener todas las relaciones
    @GetMapping
    public ResponseEntity<List<ClubesCompeticiones>> obtenerTodas() {
        return ResponseEntity.ok(clubesCompeticionesService.obtenerTodas());
    }

    // Obtener relaciones por club
    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<ClubesCompeticiones>> obtenerPorClub(@PathVariable String clubId) {
        return ResponseEntity.ok(clubesCompeticionesService.obtenerPorClub(clubId));
    }

    // Obtener relaciones por competición
    @GetMapping("/competicion/{competicionId}")
    public ResponseEntity<List<ClubesCompeticiones>> obtenerPorCompeticion(@PathVariable String competicionId) {
        return ResponseEntity.ok(clubesCompeticionesService.obtenerPorCompeticion(competicionId));
    }

    // Eliminar una relación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRelacion(@PathVariable String id) {
        clubesCompeticionesService.eliminarRelacion(id);
        return ResponseEntity.noContent().build();
    }
}

