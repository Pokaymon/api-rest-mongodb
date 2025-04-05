package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.Jugador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JugadorRepository extends MongoRepository<Jugador, String> {
    Optional<Jugador> findByNombreAndApellido(String nombre, String apellido);
}

