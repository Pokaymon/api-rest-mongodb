package com.mundialista.api_rest.services;

import com.mundialista.api_rest.models.Entrenador;
import com.mundialista.api_rest.repositories.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return entrenadorRepository.save(entrenador);
    }

    // Actualizar un entrenador existente
    public Entrenador actualizarEntrenador(String id, Entrenador entrenador) {
        if (entrenadorRepository.existsById(id)) {
            entrenador.setId(id);
            return entrenadorRepository.save(entrenador);
        } else {
            return null; // Puedes lanzar una excepción si lo prefieres
        }
    }

    // Eliminar un entrenador
    public void eliminarEntrenador(String id) {
        entrenadorRepository.deleteById(id);
    }
}

