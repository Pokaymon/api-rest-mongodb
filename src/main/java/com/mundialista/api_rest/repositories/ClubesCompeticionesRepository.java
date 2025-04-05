package com.mundialista.api_rest.repositories;

import com.mundialista.api_rest.models.ClubesCompeticiones;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ClubesCompeticionesRepository extends MongoRepository<ClubesCompeticiones, String> {
    List<ClubesCompeticiones> findByClubId(String clubId);
    List<ClubesCompeticiones> findByCompeticionId(String competicionId);
    Optional<ClubesCompeticiones> findByClubIdAndCompeticionId(String clubId, String competicionId);
}

