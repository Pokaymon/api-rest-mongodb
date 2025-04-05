package com.mundialista.api_rest.services;

import com.mundialista.api_rest.models.Asociacion;
import com.mundialista.api_rest.repositories.AsociacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AsociacionService {

    @Autowired
    private AsociacionRepository asociacionRepository;

    // Obtener todas las asociaciones
    public List<Asociacion> obtenerTodas() {
        return asociacionRepository.findAll();
    }

    // Obtener una asociación por ID
    public Asociacion obtenerPorId(String id) {
        return asociacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asociación no encontrada"));
    }

    // Crear una nueva asociación
    public Asociacion crearAsociacion(Asociacion asociacion) {
        // Verificar si ya existe
	Optional<Asociacion> AsociacionExistente = asociacionRepository.findByNombre(asociacion.getNombre());

	if (AsociacionExistente.isPresent()) {
	    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe una asociación con ese nombre");
	}
	return asociacionRepository.save(asociacion);
    }

    // Actualizar una asociación existente
    public Asociacion actualizarAsociacion(String id, Asociacion asociacion) {
        Optional<Asociacion> existente = asociacionRepository.findByNombre(asociacion.getNombre());
        if (existente.isPresent() && !existente.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe otra asociación con ese nombre");
        }

        asociacion.setId(id);
        return asociacionRepository.save(asociacion);
    }

    // Eliminar una asociación
    public void eliminarAsociacion(String id) {
        if (!asociacionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asociación no encontrada");
        }
        asociacionRepository.deleteById(id);
    }
}

