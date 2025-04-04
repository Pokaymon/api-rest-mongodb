package com.mundialista.api_rest.services;

import com.mundialista.api_rest.models.Club;
import com.mundialista.api_rest.models.ClubesJugadores;
import com.mundialista.api_rest.models.Jugador;
import com.mundialista.api_rest.repositories.ClubRepository;
import com.mundialista.api_rest.repositories.ClubesJugadoresRepository;
import com.mundialista.api_rest.repositories.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClubesJugadoresService {

    @Autowired
    private ClubesJugadoresRepository clubesJugadoresRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    // Crear una nueva relación entre un club y un jugador
    public ClubesJugadores crearRelacion(String clubId, String jugadorId) {
        Optional<Club> clubOpt = clubRepository.findById(clubId);
        Optional<Jugador> jugadorOpt = jugadorRepository.findById(jugadorId);

        if (clubOpt.isEmpty() || jugadorOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Club o Jugador no encontrados");
        }

        ClubesJugadores nuevaRelacion = new ClubesJugadores(clubOpt.get(), jugadorOpt.get());
        return clubesJugadoresRepository.save(nuevaRelacion);
    }

    // Obtener todas las relaciones
    public List<ClubesJugadores> obtenerTodas() {
        return clubesJugadoresRepository.findAll();
    }

    // Obtener relaciones por club
    public List<ClubesJugadores> obtenerPorClub(String clubId) {
        return clubesJugadoresRepository.findByClubId(clubId);
    }

    // Obtener relaciones por competición
    public List<ClubesJugadores> obtenerPorJugador(String jugadorId) {
        return clubesJugadoresRepository.findByJugadorId(jugadorId);
    }

    // Eliminar una relación
    public void eliminarRelacion(String id) {
        if (!clubesJugadoresRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relación no encontrada");
        }
        clubesJugadoresRepository.deleteById(id);
    }
}
