package com.mundialista.api_rest.services;

import com.mundialista.api_rest.models.Jugador;
import com.mundialista.api_rest.models.Club;
import com.mundialista.api_rest.repositories.JugadorRepository;
import com.mundialista.api_rest.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private ClubRepository clubRepository;

    // Obtener todos los jugadores
    public List<Jugador> obtenerTodosLosJugadores() {
        return jugadorRepository.findAll();
    }

    // Obtener un jugador por id
    public Optional<Jugador> obtenerJugadorPorId(String id) {
        id = id.trim(); // Elimina espacios en blanco
        return jugadorRepository.findById(id);
    }

    // Crear un nuevo jugador
    public Jugador crearJugador(Jugador jugador) {

        // Verificar si ya existe un jugador con el mismo nombre y apellido
        Optional<Jugador> jugadorExistente = jugadorRepository.findByNombreAndApellido(
            jugador.getNombre().trim(),
            jugador.getApellido().trim()
        );

        if (jugadorExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Ya existe un jugador con ese nombre y apellido");
        }

        // Verificar existencia del club
        Optional<Club> clubOpt = clubRepository.findById(jugador.getClub().getId());

        if (clubOpt.isPresent()) {
            // Si el club existe, lo asociamos al jugador
            jugador.setClub(clubOpt.get());
            return jugadorRepository.save(jugador);
        } else {
            // Si el club no existe, lanzamos excepción
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Club con el ID proporcionado no existe");
        }
    }

    // Actualizar un jugador existente
    public Jugador actualizarJugador(String id, Jugador jugador) {

        Optional<Jugador> jugadorExistenteOpt = jugadorRepository.findById(id);

        if (jugadorExistenteOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador no encontrado");
        }

        Jugador jugadorExistente = jugadorExistenteOpt.get();

        // Solo si son datos nuevos
	if (jugador.getDorsal() != null) {
            jugadorExistente.setDorsal(jugador.getDorsal());
        }

        if (jugador.getPosicion() != null) {
            jugadorExistente.setPosicion(jugador.getPosicion());
        }

        // Verificar y actualizar el club (si no viene null)
        if (jugador.getClub() != null && jugador.getClub().getId() != null) {
            Optional<Club> clubOpt = clubRepository.findById(jugador.getClub().getId());
            if (clubOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Club con el ID proporcionado no existe");
            }
            jugadorExistente.setClub(clubOpt.get());
        }

        return jugadorRepository.save(jugadorExistente);
        }

    // Eliminar un jugador
    public void eliminarJugador(String id) {
        jugadorRepository.deleteById(id);
    }
}

