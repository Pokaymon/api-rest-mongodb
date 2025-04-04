package com.mundialista.api_rest.controllers;

import com.mundialista.api_rest.models.ClubesJugadores;
import com.mundialista.api_rest.services.ClubesJugadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubes_jugadores")
public class ClubesJugadoresController {

    @Autowired
    private ClubesJugadoresService clubesJugadoresService;

    // Crear una nueva relación
    @PostMapping("/{clubId}/{jugadorId}")
    public ResponseEntity<ClubesJugadores> crearRelacion(
            @PathVariable String clubId, @PathVariable String jugadorId) {
        return ResponseEntity.ok(clubesJugadoresService.crearRelacion(clubId, jugadorId));
    }

    // Obtener todas las relaciones
    @GetMapping
    public ResponseEntity<List<ClubesJugadores>> obtenerTodas() {
        return ResponseEntity.ok(clubesJugadoresService.obtenerTodas());
    }

    // Obtener relaciones por club
    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<ClubesJugadores>> obtenerPorClub(@PathVariable String clubId) {
        return ResponseEntity.ok(clubesJugadoresService.obtenerPorClub(clubId));
    }

    // Obtener relaciones por competición
    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<ClubesJugadores>> obtenerPorJugador(@PathVariable String jugadorId) {
        return ResponseEntity.ok(clubesJugadoresService.obtenerPorJugador(jugadorId));
    }

    // Eliminar una relación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRelacion(@PathVariable String id) {
        clubesJugadoresService.eliminarRelacion(id);
        return ResponseEntity.noContent().build();
    }
}
