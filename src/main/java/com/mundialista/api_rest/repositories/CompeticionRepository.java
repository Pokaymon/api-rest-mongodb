package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.Competicion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CompeticionRepository extends MongoRepository<Competicion, String> {
    Optional<Competicion> findByNombreIgnoreCase(String nombre);
}

