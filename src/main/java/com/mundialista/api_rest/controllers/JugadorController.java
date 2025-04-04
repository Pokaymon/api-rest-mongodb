package com.mundialista.api_rest.controllers;

import com.mundialista.api_rest.models.Jugador;
import com.mundialista.api_rest.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    // Obtener todos los jugadores
    @GetMapping
    public List<Jugador> obtenerTodosLosJugadores() {
        return jugadorService.obtenerTodosLosJugadores();
    }

    // Obtener un jugador por id
    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtenerJugadorPorId(@PathVariable String id) {
        Optional<Jugador> jugador = jugadorService.obtenerJugadorPorId(id);
        return jugador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo jugador
    @PostMapping
    public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador) {
    try {
        Jugador nuevoJugador = jugadorService.crearJugador(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoJugador);
    } catch (ResponseStatusException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }

    // Actualizar un jugador
    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable String id, @RequestBody Jugador jugador) {
        Jugador jugadorActualizado = jugadorService.actualizarJugador(id, jugador);
        return jugadorActualizado != null ? ResponseEntity.ok(jugadorActualizado)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar un jugador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJugador(@PathVariable String id) {
        jugadorService.eliminarJugador(id);
        return ResponseEntity.noContent().build();
    }
}

