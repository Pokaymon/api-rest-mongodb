package com.mundialista.api_rest.services;

import com.mundialista.api_rest.models.Club;
import com.mundialista.api_rest.models.Entrenador;
import com.mundialista.api_rest.models.Asociacion;
import com.mundialista.api_rest.repositories.ClubRepository;
import com.mundialista.api_rest.repositories.EntrenadorRepository;
import com.mundialista.api_rest.repositories.AsociacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private AsociacionRepository asociacionRepository;

    // Obtener todos los clubes
    public List<Club> obtenerTodosLosClubes() {
        return clubRepository.findAll();
    }

    // Obtener un club por id
    public Optional<Club> obtenerClubPorId(String id) {
        return clubRepository.findById(id);
    }

    // Crear un nuevo club
    public Club crearClub(Club club) {

        // Verificar si ya existe un club con el mismo nombre
        Optional<Club> existente = clubRepository.findByNombreIgnoreCase(club.getNombre().trim());
        if (existente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un club con ese nombre");
        }

        // Verificar existencia del entrenador

	Optional<Entrenador> entrenadorOpt = entrenadorRepository.findById(club.getEntrenador().getId());
	Optional<Asociacion> asociacionOpt = asociacionRepository.findById(club.getAsociacion().getId());

	if (entrenadorOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El entrenador con el ID proporcionado no existe");
        }

	if (asociacionOpt.isEmpty()) {
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La Asociacion con el ID proporcionado no existe");
	}

	// Asociar el entrenador y la asociación al club
	club.setEntrenador(entrenadorOpt.get());
	club.setAsociacion(asociacionOpt.get());

	// Guardar y retornar
	return clubRepository.save(club);
    }

    // Actualizar un club existente
    public Club actualizarClub(String id, Club club) {
        Optional<Club> clubExistenteOpt = clubRepository.findById(id);

        if (clubExistenteOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Club No encontrado");
        }

        Club clubExistente = clubExistenteOpt.get();


	// Verificar si el nuevo nombre ya existe en otro club
        Optional<Club> otroClub = clubRepository.findByNombreIgnoreCase(club.getNombre().trim());
        if (otroClub.isPresent() && !otroClub.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe otro club con ese nombre");
        }

	// Actualizar campos básicos
	clubExistente.setNombre(club.getNombre());

	// Verificar y actualizar el entrenador (si no esta null)
	if (club.getEntrenador() != null && club.getEntrenador().getId() != null) {
	    Optional<Entrenador> entrenadorOpt = entrenadorRepository.findById(club.getEntrenador().getId());
	    if (entrenadorOpt.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El club con el ID proporcionado no existe");
	    }
	    clubExistente.setEntrenador(entrenadorOpt.get());
	}

	// Verificar y actualizar la asociación (si no está null)
        if (club.getAsociacion() != null && club.getAsociacion().getId() != null) {
           Optional<Asociacion> asociacionOpt = asociacionRepository.findById(club.getAsociacion().getId());
           if (asociacionOpt.isEmpty()) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La asociación con el ID proporcionado no existe");
           }
           clubExistente.setAsociacion(asociacionOpt.get());
        }

	return clubRepository.save(clubExistente);
    }

    // Eliminar un club
    public void eliminarClub(String id) {
        clubRepository.deleteById(id);
    }
}

