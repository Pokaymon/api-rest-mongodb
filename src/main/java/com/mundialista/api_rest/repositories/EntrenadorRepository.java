package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.Entrenador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EntrenadorRepository extends MongoRepository<Entrenador, String> {
    Optional<Entrenador> findByNombreAndApellido(String nombre, String apellido);
}

