package com.mundialista.api_rest.services;

import com.mundialista.api_rest.models.Competicion;
import com.mundialista.api_rest.repositories.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class CompeticionService {

    @Autowired
    private CompeticionRepository competicionRepository;

    // Obtener todas las competiciones
    public List<Competicion> obtenerTodasLasCompeticiones() {
        return competicionRepository.findAll();
    }

    // Obtener una competicion por su id
    public Optional<Competicion> obtenerCompeticionPorId(String id) {
        return competicionRepository.findById(id);
    }

    // Crear una nueva competicion
    public Competicion crearCompeticion(Competicion competicion) {
        Optional<Competicion> existente = competicionRepository.findByNombreIgnoreCase(competicion.getNombre().trim());
        if (existente.isPresent()) {
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una competición con ese nombre");
        }
        return competicionRepository.save(competicion);
    }

    // Actualizar una competicion existente
    public Competicion actualizarCompeticion(String id, Competicion competicion) {
        Optional<Competicion> existente = competicionRepository.findByNombreIgnoreCase(competicion.getNombre().trim());
        if (existente.isPresent() && !existente.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe otra competición con ese nombre");
        }

        competicion.setId(id);
        return competicionRepository.save(competicion);
    }

    // Eliminar una competicion
    public void eliminarCompeticion(String id) {
        competicionRepository.deleteById(id);
    }
}

