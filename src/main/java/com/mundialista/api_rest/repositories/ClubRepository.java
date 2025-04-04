package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.Club;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends MongoRepository<Club, String> {
    // Aquí puedes agregar consultas personalizadas si es necesario
}

