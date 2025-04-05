package com.mundialista.api_rest.services;

import com.mundialista.api_rest.models.Entrenador;
import com.mundialista.api_rest.repositories.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    // Obtener todos los entrenadores
    public List<Entrenador> obtenerTodosLosEntrenadores() {
        return entrenadorRepository.findAll();
    }

    // Obtener un entrenador por su id
    public Optional<Entrenador> obtenerEntrenadorPorId(String id) {
        return entrenadorRepository.findById(id);
    }

    // Crear un nuevo entrenador
    public Entrenador crearEntrenador(Entrenador entrenador) {
        // Verificar si ya existe un jugador con el mismo nombre y apellido
        Optional<Entrenador> entrenadorExistente = entrenadorRepository.findByNombreAndApellido(
            entrenador.getNombre().trim(),
            entrenador.getApellido().trim()
        );

        if (entrenadorExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Ya existe un entrenador con ese nombre y apellido");
        }

	return entrenadorRepository.save(entrenador);
    }

    // Actualizar un entrenador existente
    public Entrenador actualizarEntrenador(String id, Entrenador entrenador) {
        Optional<Entrenador> entrenadorExistenteOpt = entrenadorRepository.findById(id);

        if (entrenadorExistenteOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrenador no encontrado");
        }

	Entrenador entrenadorExistente = entrenadorExistenteOpt.get();

        // Solo si son datos nuevos
	if (entrenador.getEdad() != null) {
            entrenadorExistente.setEdad(entrenador.getEdad());
        }

        if (entrenador.getNacionalidad() != null) {
            entrenadorExistente.setNacionalidad(entrenador.getNacionalidad());
        }

	return entrenadorRepository.save(entrenadorExistente);
    }

    // Eliminar un entrenador
    public void eliminarEntrenador(String id) {
        entrenadorRepository.deleteById(id);
    }
}

