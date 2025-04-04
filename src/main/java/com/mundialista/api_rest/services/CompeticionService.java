package com.mundialista.api_rest.services;

import com.mundialista.api_rest.models.Competicion;
import com.mundialista.api_rest.repositories.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return competicionRepository.save(competicion);
    }

    // Actualizar una competicion existente
    public Competicion actualizarCompeticion(String id, Competicion competicion) {
        competicion.setId(id);
        return competicionRepository.save(competicion);
    }

    // Eliminar una competicion
    public void eliminarCompeticion(String id) {
        competicionRepository.deleteById(id);
    }
}

