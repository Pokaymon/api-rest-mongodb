package com.mundialista.api_rest.repositories;

import java.util.Optional;
import com.mundialista.api_rest.models.Asociacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociacionRepository extends MongoRepository<Asociacion, String> {
    Optional<Asociacion> findByNombre(String nombre);
}

