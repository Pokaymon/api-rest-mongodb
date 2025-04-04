package com.mundialista.api_rest.controllers;

import com.mundialista.api_rest.models.Club;
import com.mundialista.api_rest.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clubes")
public class ClubController {

    @Autowired
    private ClubService clubService;

    // Obtener todos los clubes
    @GetMapping
    public List<Club> obtenerTodosLosClubes() {
        return clubService.obtenerTodosLosClubes();
    }

    // Obtener un club por id
    @GetMapping("/{id}")
    public ResponseEntity<Club> obtenerClubPorId(@PathVariable String id) {
        Optional<Club> club = clubService.obtenerClubPorId(id);
        return club.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo club
    @PostMapping
    public ResponseEntity<Club> crearClub(@RequestBody Club club) {
	try {
	    Club nuevoClub = clubService.crearClub(club);
	    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoClub);
	} catch (ResponseStatusException e) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
    }

    // Actualizar un club
    @PutMapping("/{id}")
    public ResponseEntity<Club> actualizarClub(@PathVariable String id, @RequestBody Club club) {
        Club clubActualizado = clubService.actualizarClub(id, club);
        return clubActualizado != null ? ResponseEntity.ok(clubActualizado)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar un club
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarClub(@PathVariable String id) {
        clubService.eliminarClub(id);
        return ResponseEntity.noContent().build();
    }
}

