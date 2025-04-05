package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.Club;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClubRepository extends MongoRepository<Club, String> {
    Optional<Club> findByNombreIgnoreCase(String nombre);
}

