package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.Jugador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends MongoRepository<Jugador, String> {
    // Aquí se pueden agregar métodos personalizados si es necesario
}

