package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.Entrenador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends MongoRepository<Entrenador, String> {
    // Aquí podemos agregar métodos personalizados si es necesario
}

